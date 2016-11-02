package nl.mollie;

/**
 * Aggregate root of the Mollie API client.
 */
public class Mollie {
	public static MollieClient client() {
		return new MollieClient();
	}

	public static MollieClient client(String apiKey) {
		MollieClient client = new MollieClient();
		client.setApiKey(apiKey);
		return client;
	}
}
