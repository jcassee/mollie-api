package nl.mollie.payments;

import static nl.mollie.util.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import nl.mollie.MollieClient;
import nl.mollie.util.Preconditions;
import nl.mollie.ResultPage;

public class PaymentsModule implements Iterable<Payment> {
	public static final String PAYMENTS_PATH = "payments";

	private MollieClient client;

	public PaymentsModule(MollieClient client) {
		this.client = client;
	}

	public Payment create(@Nonnull PaymentRequest request) throws IOException {
		return client.request(MollieClient.POST, PAYMENTS_PATH, request, Payment.class);
    }

    public Payment get(@Nonnull String id) throws IOException {
	    return client.request(MollieClient.GET, MollieClient.joinPaths(PAYMENTS_PATH, Preconditions.checkNotNull(id)), null, Payment.class);
    }

    public ResultPage<Payment> list(Integer offset, Integer count) throws IOException {
	    String path = buildListPath(offset, count);
	    return client.request(MollieClient.GET, path, null, new TypeReference<ResultPage<Payment>>() {});
    }

	public Iterator<Payment> iterator() {
		return client.resultIterator(buildListPath(0, MollieClient.ITERATOR_PAGE_SIZE));
	}

	private String buildListPath(Integer offset, Integer count) {
		URIBuilder uriBuilder =  MollieClient.uriBuilder(PAYMENTS_PATH);
		if (offset != null) uriBuilder.setParameter("offset", offset.toString());
		if (count != null) uriBuilder.setParameter("count", count.toString());
		return uriBuilder.toString();
	}
}
