package nl.mollie.payments;

import nl.mollie.issuers.Issuer;
import nl.mollie.issuers.IssuersModule;

/**
 * Request parameters for creating a new iDEAL payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-ideal">The Mollie Create Payment
 * iDEAL method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class IdealPaymentRequest extends PaymentRequest<IdealPaymentRequest> {

	private String issuer;

	protected IdealPaymentRequest() {
		super("ideal");
	}

	/**
	 * Sets an iDEAL issuer. The returned payment URL will deep-link into the specific banking website.
	 *
	 * @param issuer the issuer
	 *
	 * @return this object for chaining
	 *
	 * @see IssuersModule
	 */
	public IdealPaymentRequest issuer(Issuer issuer) {
		return issuer(issuer != null ? issuer.getId() : null);
	}

	/**
	 /**
	 * Sets an iDEAL issuer ID, for example {@code ideal_INGNL2A}. The returned payment URL will deep-link into the
	 * specific banking website (ING Bank, in this example).
	 *
	 * @param issuer the issuer ID
	 *
	 * @return this object for chaining
	 *
	 * @see #issuer(Issuer)
	 */
	public IdealPaymentRequest issuer(String issuer) {
		this.issuer = issuer;
		return this;
	}
}
