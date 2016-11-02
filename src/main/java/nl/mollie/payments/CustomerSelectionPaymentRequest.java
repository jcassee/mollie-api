package nl.mollie.payments;

/**
 * Request parameters for creating a new payment without an explicit payment method.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-ideal">The Mollie Create Payment
 * iDEAL method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class CustomerSelectionPaymentRequest extends PaymentRequest<CustomerSelectionPaymentRequest> {

	protected CustomerSelectionPaymentRequest(String method) {
		super(method);
	}
}
