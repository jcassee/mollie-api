package nl.mollie;

import static java.util.Collections.emptyIterator;
import static nl.mollie.MollieClient.GET;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;

public class ResultIterator<E extends PageableEntity> implements Iterator<E> {
	private MollieClient client;
	private String nextPageUrl;
	private int remaining;
	private Iterator<E> iterator;
	private Set<String> seenIds;

	ResultIterator(MollieClient client, String url) {
		this.client = client;
		this.nextPageUrl = url;
		this.remaining = -1;
		this.iterator = emptyIterator();
		this.seenIds = new HashSet<>();
	}

	@Override
	public boolean hasNext() {
		if (remaining == -1) loadNextPage();
		return remaining > 0;
	}

	@Override
	public E next() {
		if (remaining == 0) throw new NoSuchElementException();
		while (true) {
			if (!iterator.hasNext()) loadNextPage();
			E next = tryNext();
			if (next == null) continue;
			return next;
		}
	}

	private void loadNextPage() {
		try {
			ResultPage<E> page = loadPage(nextPageUrl);
			nextPageUrl = page.getLink(ResultPage.NEXT);
			iterator = page.getData().iterator();
			if (remaining == -1) this.remaining = page.getTotalCount() - page.getOffset();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private ResultPage<E> loadPage(String url) throws IOException {
		return client.request(GET, URI.create(url), null, new TypeReference<ResultPage<E>>() {});
	}

	private E tryNext() {
		E next = iterator.next();
		if (!hasSeen(next)) {
			addToSeen(next);
			remaining--;
			return next;
		} else {
			return null;
		}
	}

	private boolean hasSeen(E result) {
		return seenIds.contains(result.getId());
	}

	private void addToSeen(E result) {
		seenIds.add(result.getId());
	}
}
