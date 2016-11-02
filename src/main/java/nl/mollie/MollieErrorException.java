package nl.mollie;

import org.apache.http.client.HttpResponseException;

public class MollieErrorException extends HttpResponseException {
	private MollieError error;

	public MollieErrorException(int statusCode, String reason, MollieError error) {
		super(statusCode, reason);
		this.error = error;
	}

	public MollieError getError() {
		return error;
	}
}
