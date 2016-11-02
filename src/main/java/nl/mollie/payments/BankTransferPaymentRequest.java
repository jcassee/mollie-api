package nl.mollie.payments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Request parameters for creating a new bank transfer payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-banktransfer">The Mollie Create
 * Payment bank transfer method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class BankTransferPaymentRequest extends PaymentRequest<BankTransferPaymentRequest> {

	private String billingEmail;
	private String dueDate;

	protected BankTransferPaymentRequest() {
		super("banktransfer");
	}

	/**
	 * Sets the consumer's email address, to automatically send the bank transfer details to. <em>Please note</em>: the
	 * payment instructions will be sent immediately when creating the payment. If you don't specify the {@code locale}
	 * parameter, the email will be sent in English, as we haven't yet been able to detect the consumer's browser
	 * language.
	 *
	 * @param billingEmail the consumer email address to send bank transfer details to
	 *
	 * @return this object for chaining
	 */
	public BankTransferPaymentRequest billingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
		return this;
	}

	/**
	 * Sets the date the payment should expire, in {@code YYYY-MM-DD} format. <em>Please note</em>: The minimum date is
	 * tomorrow and the maximum date is 100 days in the future.
	 *
	 * @param dueDate the payment due date
	 *
	 * @return this object for chaining
	 */
	public BankTransferPaymentRequest dueDate(String dueDate) {
		// TODO: check argument
		this.dueDate = dueDate;
		return this;
	}

	/**
	 * Sets the date the payment should expire. <em>Please note</em>: The minimum date is tomorrow and the maximum date
	 * is 100 days in the future.
	 *
	 * @param dueDate the payment due date
	 *
	 * @return this object for chaining
	 */
	public BankTransferPaymentRequest dueDate(LocalDate dueDate) {
		// TODO: check argument
		this.dueDate = dueDate != null ? dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
		return this;
	}
}
