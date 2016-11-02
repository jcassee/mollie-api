package nl.mollie.methods;

import static nl.mollie.util.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import nl.mollie.MollieClient;
import nl.mollie.ResultPage;
import nl.mollie.util.Preconditions;

public class MethodsModule implements Iterable<Method> {
	public static final String METHODS_PATH = "methods";

	private MollieClient client;

	public MethodsModule(MollieClient client) {
		this.client = client;
	}

	public Method get(@Nonnull String id) throws IOException {
		return client.request(MollieClient.GET, MollieClient.joinPaths(METHODS_PATH, Preconditions.checkNotNull(id)), null, Method.class);
	}

	public ResultPage<Method> list(String recurringType, String locale, Integer offset, Integer count) throws IOException {
		String path = buildListPath(recurringType, locale, offset, count);
		return client.request(MollieClient.GET, path, null, new TypeReference<ResultPage<Method>>() {});
	}

	public Iterator<Method> iterator() {
		return client.resultIterator(buildListPath(null, null, 0, MollieClient.ITERATOR_PAGE_SIZE));
	}

	private String buildListPath(String recurringType, String locale, Integer offset, Integer count) {
		URIBuilder uriBuilder =  MollieClient.uriBuilder(METHODS_PATH);
		if (recurringType != null) uriBuilder.setParameter("recurringType", recurringType);
		if (locale != null) uriBuilder.setParameter("locale", locale);
		if (offset != null) uriBuilder.setParameter("offset", offset.toString());
		if (count != null) uriBuilder.setParameter("count", count.toString());
		return uriBuilder.toString();
	}
}
