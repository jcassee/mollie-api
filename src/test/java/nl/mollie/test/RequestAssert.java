package nl.mollie.test;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.InputStreamAssert;
import org.assertj.core.api.StringAssert;
import org.assertj.core.api.exception.RuntimeIOException;

import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;

public class RequestAssert extends AbstractAssert<RequestAssert, HttpRequest> {

	public RequestAssert(HttpRequest actual) {
		super(actual, RequestAssert.class);
	}

	public StringAssert method() {
		return new StringAssert(actual.getRequestLine().getMethod());
	}

	public ContentTypeAssert contentType() {
		Header header = actual.getFirstHeader(HttpHeaders.CONTENT_TYPE);
		String headerValue = header != null ? header.getValue() : null;
		ContentType contentType = headerValue != null ? ContentType.parse(headerValue) : null;
		return new ContentTypeAssert(contentType);
	}

	public InputStreamAssert content() {
		HttpEntity entity = null;
		try {
			InputStream inputStream = getEntity(actual).getContent();
			return new InputStreamAssert(inputStream);
		} catch (IOException e) {
			throw new RuntimeIOException("Unable to get entity", e);
		}
	}

	public JsonFluentAssert contentAsJson() {
		try {
			String entity = getEntityAsString(actual);
			return assertThatJson(entity);
		} catch (IOException e) {
			throw new RuntimeIOException("Unable to get entity", e);
		}
	}

	private HttpEntity getEntity(HttpRequest request) {
		HttpEntityEnclosingRequest entityRequest = null;
		HttpEntity entity = null;
		if (actual instanceof HttpEntityEnclosingRequest) {
			entityRequest = (HttpEntityEnclosingRequest) request;
			entity = entityRequest.getEntity();
		}
		if (entity == null) failWithMessage("Expected resource entity");
		assert entity != null;  // failWithMessage does not return

		// Make sure entity can be retrieved again
		try {
			if (!entity.isRepeatable()) {
				entity = new BufferedHttpEntity(entity);
				entityRequest.setEntity(entity);
			}
		} catch (IOException e) {
			throw new RuntimeIOException("Unable to read entity", e);
		}
		return entity;
	}

	private String getEntityAsString(HttpRequest request) throws IOException {
		HttpEntity entity = getEntity(request);
		Charset charset = ContentType.getOrDefault(entity).getCharset();
		if (charset == null) charset = Consts.ISO_8859_1;
		int contentLength = (int) entity.getContentLength();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(contentLength >= 0 ? contentLength : 32);
		entity.writeTo(outputStream);
		return outputStream.toString(charset.name());
	}
}
