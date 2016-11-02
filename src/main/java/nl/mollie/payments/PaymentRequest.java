package nl.mollie.payments;

import javax.annotation.Nullable;

/**
 * Request parameters for creating a new payment.
 * <p>
 * Start creating a payment request by selecting a payment method using any of this class' static methods.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters">Mollie Create Payment parameters
 * documentation</a>
 */
public abstract class PaymentRequest<B extends PaymentRequest> extends BasePaymentRequest<B> {

	/**
	 * When not using a method, a payment method selection screen is shown.
	 *
	 * @return a payment request without a payment method
	 */
	public static CustomerSelectionPaymentRequest noMethod() {
		return new CustomerSelectionPaymentRequest(null);
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the credit card
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the credit card payment method 
	 */
	public static CreditCardPaymentRequest creditCard() {
		return new CreditCardPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the SOFORT
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the SOFORT payment method 
	 */
	public static MethodPaymentRequest sofort() {
		return new MethodPaymentRequest("sofort");
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the iDEAL
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the iDEAL payment method 
	 */
	public static IdealPaymentRequest ideal() {
		return new IdealPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the Mister Cash
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the Mister Cash payment method 
	 */
	public static MethodPaymentRequest misterCash() {
		return new MethodPaymentRequest("mistercach");
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the bank
	 * transfer payment method. The parameter enables you to fully integrate the payment method selection into your
	 * website, however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the bank transfer payment method 
	 */
	public static BankTransferPaymentRequest bankTransfer() {
		return new BankTransferPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the SEPA Direct
	 * Debit payment method. The parameter enables you to fully integrate the payment method selection into your
	 * website, however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the SEPA Direct Debit payment method 
	 */
	public static DirectDebitPaymentRequest directDebit() {
		return new DirectDebitPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the PayPal
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the PayPal payment method 
	 */
	public static PayPalPaymentRequest payPal() {
		return new PayPalPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the Bitcoin
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the Bitcoin payment method 
	 */
	public static MethodPaymentRequest bitcoin() {
		return new MethodPaymentRequest("bitcoin");
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the Podium
	 * Cadeaukaart payment method. The parameter enables you to fully integrate the payment method selection into your
	 * website, however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the Podium Cadeaukaart payment method 
	 */
	public static MethodPaymentRequest podiumCadeaukaart() {
		return new MethodPaymentRequest("podiumcadeaukaart");
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the paysafecard
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the paysafecard payment method 
	 */
	public static PaysafecardPaymentRequest paysafecard() {
		return new PaysafecardPaymentRequest();
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the KBC/CBC
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 * <p>
	 * When this payment method is chosen, the {@code description} will be truncated to 13 characters. This will be
	 * increased in the future.
	 *
	 * @return a payment request with the KBC/CBC payment method 
	 */
	public static MethodPaymentRequest kbc() {
		return new MethodPaymentRequest("kbc");
	}

	/**
	 * When using this method, your customer will skip the selection screen and will be sent directly to the Belfius
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @return a payment request with the Belfius payment method 
	 */
	public static MethodPaymentRequest belfius() {
		return new MethodPaymentRequest("belfius");
	}

	/**
	 * When using this parameter, your customer will skip the selection screen and will be sent directly to the chosen
	 * payment method. The parameter enables you to fully integrate the payment method selection into your website,
	 * however note Mollie's country based conversion optimization is lost.
	 *
	 * @param method The payment method. For possible values, see the <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters">the
	 * Mollie Create Payment parameters documentation</a>
	 *
	 * @return a payment request with the specified payment method 
	 *
	 * @apiNote Use this constructor only if you need to use a payment method that this library does not support
	 * natively. In that case, please also create an issue in the <a href="https://github.com/stil4m/mollie-api/issues">mollie-api
	 * issue tracker</a>.
	 */
	public static MethodPaymentRequest method(String method) {
		return new MethodPaymentRequest(method);
	}

	protected PaymentRequest(@Nullable String method) {
		super(method);
	}
}
