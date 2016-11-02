package nl.mollie.payments;

/**
 * Request parameters for creating a new payment with a payment method.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-ideal">The Mollie Create Payment
 * iDEAL method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class MethodPaymentRequest extends PaymentRequest<MethodPaymentRequest> {

	protected MethodPaymentRequest(String method) {
		super(method);
	}
}
