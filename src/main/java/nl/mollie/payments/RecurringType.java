package nl.mollie.payments;

/**
 * Parameter for {@link PaymentRequest} that enables recurring payments.
 *
 * @see <a href="https://www.mollie.com/en/docs/recurring">The Mollie recurring payments documentation</a>
 */
public enum RecurringType {
	/**
	 * A first payment for the customer is created, allowing the customer to agree to automatic recurring charges taking
	 * place on their account in the future.
	 */
	FIRST,

	/**
	 * The customer's card is charged automatically.
	 */
	RECURRING;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
