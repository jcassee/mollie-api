package nl.mollie.refunds;

import static nl.mollie.payments.PaymentsModule.PAYMENTS_PATH;
import static nl.mollie.util.Preconditions.checkNotEmpty;
import static nl.mollie.util.Preconditions.checkNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import nl.mollie.MollieClient;
import nl.mollie.util.Preconditions;
import nl.mollie.ResultPage;
import nl.mollie.payments.Payment;

public class RefundsModule implements Iterable<Refund> {
	public static final String REFUNDS_PATH = "refunds";

	private MollieClient client;

	public RefundsModule(MollieClient client) {
		this.client = client;
	}

	public Refund create(@Nonnull Payment payment, BigDecimal amount, String description) throws IOException {
	    return create(Preconditions.checkNotNull(payment).getId(), amount, description);
    }

    public Refund create(@Nonnull String paymentId, BigDecimal amount, String description) throws IOException {
	    String path = MollieClient.joinPaths(PAYMENTS_PATH, Preconditions.checkNotEmpty(paymentId), REFUNDS_PATH);

	    URIBuilder pathBuilder = new URIBuilder(URI.create(path));
	    if (amount != null) pathBuilder.setParameter("amount", amount.toString());
	    if (description != null) pathBuilder.setParameter("description", description);

	    return client.request(MollieClient.POST, pathBuilder.toString(), null, Refund.class);
    }

    public Refund get(@Nonnull Payment payment, @Nonnull String id) throws IOException {
	    return get(Preconditions.checkNotNull(payment).getId(), id);
    }

    public Refund get(@Nonnull String paymentId, @Nonnull String id) throws IOException {
	    String path = MollieClient.joinPaths(PAYMENTS_PATH, Preconditions.checkNotEmpty(paymentId), REFUNDS_PATH, Preconditions.checkNotEmpty(id));
	    return client.request(MollieClient.GET, path, null, Refund.class);
    }

    public void cancel(@Nonnull Refund refund) throws IOException {
	    cancel(Preconditions.checkNotNull(refund).getPayment(), refund.getId());
    }

    public void cancel(@Nonnull Payment payment, @Nonnull String refundId) throws IOException {
	    cancel(Preconditions.checkNotNull(payment).getId(), refundId);
    }

    public void cancel(@Nonnull String paymentId, @Nonnull String refundId) throws IOException {
	    String path = MollieClient.joinPaths(PAYMENTS_PATH, Preconditions.checkNotEmpty(paymentId), REFUNDS_PATH, Preconditions.checkNotEmpty(refundId));
	    client.request(MollieClient.DELETE, path, null, Void.class);
    }

	public ResultPage<Refund> list(Integer offset, Integer count) throws IOException {
		String path = buildListPath(offset, count);
		return client.request(MollieClient.GET, path, null, new TypeReference<ResultPage<Refund>>() {});
	}

	public ResultPage<Refund> list(@Nonnull Payment payment, Integer offset, Integer count) throws IOException {
		return list(Preconditions.checkNotNull(payment).getId(), offset, count);
	}

	public ResultPage<Refund> list(@Nonnull String paymentId, Integer offset, Integer count) throws IOException {
		String path = buildListPath(paymentId, offset, count);
		return client.request(MollieClient.GET, path, null, new TypeReference<ResultPage<Refund>>() {});
	}

	public Iterator<Refund> iterator() {
		return client.resultIterator(buildListPath(0, MollieClient.ITERATOR_PAGE_SIZE));
	}

	public Iterator<Refund> iterator(@Nonnull Payment payment) {
		return iterator(Preconditions.checkNotNull(payment).getId());
	}

	public Iterator<Refund> iterator(@Nonnull String paymentId) {
		return client.resultIterator(buildListPath(paymentId, 0, MollieClient.ITERATOR_PAGE_SIZE));
	}

	private String buildListPath(Integer offset, Integer count) {
		URIBuilder uriBuilder =  MollieClient.uriBuilder(REFUNDS_PATH);
		if (offset != null) uriBuilder.setParameter("offset", offset.toString());
		if (count != null) uriBuilder.setParameter("count", count.toString());
		return uriBuilder.toString();
	}

	private String buildListPath(@Nonnull String paymentId, Integer offset, Integer count) {
		String path = MollieClient.joinPaths(PAYMENTS_PATH, Preconditions.checkNotEmpty(paymentId), REFUNDS_PATH);
		URIBuilder uriBuilder = MollieClient.uriBuilder(path);
		if (offset != null) uriBuilder.setParameter("offset", offset.toString());
		if (count != null) uriBuilder.setParameter("count", count.toString());
		return uriBuilder.toString();
	}
}
