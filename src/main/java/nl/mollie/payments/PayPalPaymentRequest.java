package nl.mollie.payments;

/**
 * Request parameters for creating a new PayPal payment.
 *
 * @see <a href="https://www.mollie.com/en/docs/reference/payments/create#parameters-paypal">The Mollie Create Payment
 * PayPal method parameters documentation</a>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PayPalPaymentRequest extends PaymentRequest<PayPalPaymentRequest> {

	private String shippingAddress;
	private String shippingCity;
	private String shippingRegion;
	private String shippingPostal;
	private String shippingCountry;

	protected PayPalPaymentRequest() {
		super("paypal");
	}

	/**
	 * Sets the shipping address. We advise to provide these details to improve PayPal's fraude protection, and thus
	 * improve conversion. The maximum character length is 128.
	 *
	 * @param shippingAddress the shipping address
	 *
	 * @return this object for chaining
	 */
	public PayPalPaymentRequest shippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}

	/**
	 * Sets the city of the shipping address. The maximum character length is 100.
	 *
	 * @param shippingCity the shipping address
	 *
	 * @return this object for chaining
	 */
	public PayPalPaymentRequest shippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
		return this;
	}

	/**
	 * Sets the region of the shipping address. The maximum character length is 100. This field is required if the
	 * {@code shippingCountry} is one of the following countries: {@code AR} {@code BR} {@code CA} {@code CN} {@code ID}
	 * {@code IN} {@code JP} {@code MX} {@code TH} {@code US}
	 *
	 * @param shippingRegion the shipping region
	 *
	 * @return this object for chaining
	 */
	public PayPalPaymentRequest shippingRegion(String shippingRegion) {
		this.shippingRegion = shippingRegion;
		return this;
	}

	/**
	 * Sets the postal code of the shipping address. The maximum character length is 20.
	 *
	 * @param shippingPostal the shipping postal code
	 *
	 * @return this object for chaining
	 */
	public PayPalPaymentRequest shippingPostal(String shippingPostal) {
		this.shippingPostal = shippingPostal;
		return this;
	}

	/**
	 * Sets he country of the shipping address, in <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1
	 * alpha-2</a> format.
	 *
	 * @param shippingCountry the shipping country code
	 *
	 * @return this object for chaining
	 */
	public PayPalPaymentRequest shippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
		return this;
	}
}
