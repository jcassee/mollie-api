package nl.mollie;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.annotation.PropertyAccessor.GETTER;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static nl.mollie.util.Preconditions.checkNotNull;
import static nl.mollie.util.Preconditions.checkState;
import static nl.mollie.util.Strings.trim;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import nl.mollie.util.Preconditions;
import nl.mollie.issuers.IssuersModule;
import nl.mollie.methods.MethodsModule;
import nl.mollie.payments.PaymentsModule;
import nl.mollie.refunds.RefundsModule;

public class MollieClient {
	public static final String DEFAULT_ENDPOINT = "https://api.mollie.com/v1";
	public static final int ITERATOR_PAGE_SIZE = 250;

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";

	private String apiKey;
	private String endpoint;
	private CloseableHttpClient httpClient;
	private ObjectMapper objectMapper;

	/**
	 * Use {@link Mollie#client} to create a new instance.
	 */
	MollieClient() {
		apiKey = null;
		this.endpoint = DEFAULT_ENDPOINT;

		httpClient = HttpClientBuilder.create()
				.disableCookieManagement()
				.build();

		objectMapper = new ObjectMapper()
				.setVisibility(FIELD, ANY)
				.setVisibility(GETTER, NONE)
				.setSerializationInclusion(NON_ABSENT)
				.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new JavaTimeModule());
	}

	// Getters and setters

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	// Modules

	public IssuersModule issuers() {
		return new IssuersModule(this);
	}

	public MethodsModule methods() {
		return new MethodsModule(this);
	}

	public PaymentsModule payments() {
		return new PaymentsModule(this);
	}

	public RefundsModule refunds() {
		return new RefundsModule(this);
	}

	// API requests

	public <T> T request(@Nonnull String method, @Nonnull String path, Object entity, @Nonnull Class<T> responseClass) throws IOException {
		JavaType responseType = objectMapper.getTypeFactory().constructType(responseClass);
		return request(method, path, entity, responseType);
	}

	public <T> T request(@Nonnull String method, @Nonnull String path, Object entity, @Nonnull TypeReference<T> responseTypeReference) throws IOException {
		JavaType responseType = objectMapper.getTypeFactory().constructType(responseTypeReference);
		return request(method, path, entity, responseType);
	}

	public <T> T request(@Nonnull String method, @Nonnull String path, Object entity, @Nonnull JavaType responseType) throws IOException {
		URI uri = URI.create(joinPaths(endpoint, path));
		return request(method, uri, entity, responseType);
	}

	public <T> T request(@Nonnull String method, @Nonnull URI uri, Object entity, @Nonnull TypeReference<T> responseTypeReference) throws IOException {
		JavaType responseType = objectMapper.getTypeFactory().constructType(responseTypeReference);
		return request(method, uri, entity, responseType);
	}

	public <T> T request(@Nonnull String method, @Nonnull URI uri, Object entity, @Nonnull JavaType responseType) throws IOException {
		HttpEntity requestEntity = new StringEntity(objectMapper.writeValueAsString(entity), APPLICATION_JSON);
		HttpUriRequest request = RequestBuilder.create(method).setUri(uri).setEntity(requestEntity).build();

		if (request.getRequestLine().getUri().startsWith(endpoint)) {  // do not leak credentials
			Preconditions.checkState(this.apiKey != null, "api key is null");
			request.addHeader(AUTHORIZATION, String.format("Bearer %s", this.apiKey));
		}

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			HttpEntity responseEntity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status <= 300) {
				return objectMapper.readValue(responseEntity.getContent(), responseType);
			} else {
				if (responseEntity != null) {
					try {
						MollieError.Wrapper errorWrapper = objectMapper.readValue(responseEntity.getContent(), MollieError.Wrapper.class);
						throw new MollieErrorException(status, response.getStatusLine().getReasonPhrase(), errorWrapper.getError());
					} catch (IOException e) {
						// Could not parse the error, pass through
					}
				}
				throw new HttpResponseException(status, response.getStatusLine().getReasonPhrase());
			}
		}
	}

	public <T extends PageableEntity> ResultPage<T> navigate(@Nonnull ResultPage<T> page, @Nonnull String linkName)
			throws IOException {
		String url = page.getLink(Preconditions.checkNotNull(linkName));
		Preconditions.checkNotNull(url, "result page has no %s link", linkName);
		return request(GET, URI.create(url), null, new TypeReference<ResultPage<T>>() {});
	}

	public <T extends PageableEntity> Iterator<T> resultIterator(@Nonnull String pageUrl) {
		return new ResultIterator<T>(this, pageUrl);
	}

	// Utility methods

	public static String joinPaths(Object firstPathComponent, Object... nextPathComponents) {
		StringBuilder builder = new StringBuilder(trim(String.valueOf(firstPathComponent), '/'));
		for (Object pathComponent : nextPathComponents) {
			String component = trim(String.valueOf(pathComponent), '/');
			builder.append('/');
			builder.append(component);
		}
		return builder.toString();
	}

	public static URIBuilder uriBuilder(String uri) {
		return new URIBuilder(URI.create(uri));
	}
}
