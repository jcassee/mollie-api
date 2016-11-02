package nl.mollie.payments;

/**
 * Request parameters for creating a new SEPA Direct Debit payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-directdebit">The Mollie Create
 * Payment SEPA Direct Debit method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class DirectDebitPaymentRequest extends PaymentRequest<DirectDebitPaymentRequest> {

	private String consumerName;
	private String consumerAccount;

	protected DirectDebitPaymentRequest() {
		super("directdebit");
	}

	/**
	 * Sets the beneficiary name of the account holder.
	 *
	 * @param consumerName the account holder's name
	 *
	 * @return this object for chaining
	 */
	public DirectDebitPaymentRequest consumerName(String consumerName) {
		this.consumerName = consumerName;
		return this;
	}

	/**
	 * Sets the IBAN of the account holder.
	 *
	 * @param consumerAccount the account holder's IBAN
	 *
	 * @return this object for chaining
	 */
	public DirectDebitPaymentRequest consumerAccount(String consumerAccount) {
		this.consumerAccount = consumerAccount;
		return this;
	}
}
