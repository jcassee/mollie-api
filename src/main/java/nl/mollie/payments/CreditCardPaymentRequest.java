package nl.mollie.payments;

/**
 * Request parameters for creating a new credit card payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-creditcard">The Mollie Create
 * Payment credit card method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class CreditCardPaymentRequest extends PaymentRequest<CreditCardPaymentRequest> {

	private String billingAddress;
	private String billingCity;
	private String billingRegion;
	private String billingPostal;
	private String billingCountry;
	private String shippingAddress;
	private String shippingCity;
	private String shippingRegion;
	private String shippingPostal;
	private String shippingCountry;

	protected CreditCardPaymentRequest() {
		super("creditcard");
	}

	/**
	 * Sets the card holder's address. We advise to provide these details to improve the credit card
	 * fraud protection, and thus improve conversion.
	 *
	 * @param billingAddress the card holder's billing address
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest billingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
		return this;
	}

	/**
	 * Sets the card holder's city.
	 *
	 * @param billingCity the card holder's billing city
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest billingCity(String billingCity) {
		this.billingCity = billingCity;
		return this;
	}

	/**
	 * Sets the card holder's region.
	 *
	 * @param billingRegion the card holder's billing region
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest billingRegion(String billingRegion) {
		this.billingRegion = billingRegion;
		return this;
	}

	/**
	 * Sets the card holder's postal code.
	 *
	 * @param billingPostal the card holder's billing postal code
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest billingPostal(String billingPostal) {
		this.billingPostal = billingPostal;
		return this;
	}

	/**
	 * Sets the card holder's country in <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1
	 * alpha-2</a> format.
	 *
	 * @param billingCountry the card holder's billing country code
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest billingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
		return this;
	}

	/**
	 * Sets the shipping address. We advise to provide these details to improve the credit card fraud protection, and
	 * thus improve conversion.
	 *
	 * @param shippingAddress the shipping address
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest shippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}

	/**
	 * Sets the city of the shipping address.
	 *
	 * @param shippingCity the shipping city
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest shippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
		return this;
	}

	/**
	 * Sets the region of the shipping address.
	 *
	 * @param shippingRegion the shipping region
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest shippingRegion(String shippingRegion) {
		this.shippingRegion = shippingRegion;
		return this;
	}

	/**
	 * Sets the postal code of the shipping address.
	 *
	 * @param shippingPostal the shipping postal code
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest shippingPostal(String shippingPostal) {
		this.shippingPostal = shippingPostal;
		return this;
	}

	/**
	 * Sets the country of the shipping address, in <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO
	 * 3166-1 alpha-2</a> format.
	 *
	 * @param shippingCountry the shipping country code
	 *
	 * @return this object for chaining
	 */
	public CreditCardPaymentRequest shippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
		return this;
	}
}
