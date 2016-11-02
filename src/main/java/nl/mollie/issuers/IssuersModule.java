package nl.mollie.issuers;

import static nl.mollie.util.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import nl.mollie.MollieClient;
import nl.mollie.util.Preconditions;
import nl.mollie.ResultPage;

public class IssuersModule implements Iterable<Issuer> {
	public static final String ISSUERS_PATH = "issuers";

	private MollieClient client;

	public IssuersModule(MollieClient client) {
		this.client = client;
	}

	public Issuer get(@Nonnull String id) throws IOException {
		return client.request(MollieClient.GET, MollieClient.joinPaths(ISSUERS_PATH, Preconditions.checkNotNull(id)), null, Issuer.class);
	}

	public ResultPage<Issuer> list(Integer offset, Integer count) throws IOException {
		String path = buildListPath(offset, count);
		return client.request(MollieClient.GET, path, null, new TypeReference<ResultPage<Issuer>>() {});
	}

	public Iterator<Issuer> iterator() {
		return client.resultIterator(buildListPath(0, MollieClient.ITERATOR_PAGE_SIZE));
	}

	private String buildListPath(Integer offset, Integer count) {
		URIBuilder uriBuilder =  MollieClient.uriBuilder(ISSUERS_PATH);
		if (offset != null) uriBuilder.setParameter("offset", offset.toString());
		if (count != null) uriBuilder.setParameter("count", count.toString());
		return uriBuilder.toString();
	}
}
