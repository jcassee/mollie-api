package nl.mollie.payments;

/**
 * Request parameters for creating a new paysafecard payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-paysafecard">The Mollie Create
 * Payment paysafecard method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PaysafecardPaymentRequest extends PaymentRequest<PaysafecardPaymentRequest> {

	private String customerReference;

	protected PaysafecardPaymentRequest() {
		super("paysafecard");
	}

	/**
	 * Sets a reference used for consumer identification. For example, you could use the consumer's IP address.
	 *
	 * @param customerReference the customer reference identification
	 *
	 * @return this object for chaining
	 */
	public PaysafecardPaymentRequest customerReference(String customerReference) {
		this.customerReference = customerReference;
		return this;
	}
}
