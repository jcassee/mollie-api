package nl.mollie.payments;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.mollie.util.ValueObject;

/**
 * Base class for payment creation request parameters.
 *
 * @param <R> the instance type, used to return the correct type in setters
 */
@SuppressWarnings({"FieldCanBeLocal", "unused", "MismatchedQueryAndUpdateOfCollection", "unchecked"})
public abstract class BasePaymentRequest<R extends BasePaymentRequest> extends ValueObject {

	private BigDecimal amount;
	private String description;
	private String redirectUrl;
	private String webhookUrl;
	private String method;
	private Map<String, Object> metadata;
	private String locale;
	private String recurringType;
	private String customerId;
	private String mandateId;

	@JsonUnwrapped
	private Map<String, Object> extraParameters;

	protected BasePaymentRequest(String method) {
		this.method = method;
	}

	/**
	 * Sets the amount in EURO that you want to charge, e.g. {@code 100.00} if you would want to charge €100.00.
	 *
	 * @param amount the amount to charge
	 *
	 * @return this object for chaining
	 */
	public R amount(BigDecimal amount) {
		this.amount = amount;
		return (R) this;
	}

	/**
	 * Sets the description of the payment you're creating. This will be shown to the consumer on their card or bank
	 * statement when possible.
	 *
	 * @param description the payment description
	 *
	 * @return this object for chaining
	 */
	public R description(String description) {
		this.description = description;
		return (R) this;
	}

	/**
	 * Sets the URL the consumer will be redirected to after the payment process. It could make sense for the redirect
	 * URL to contain a unique identifier – like your order ID – so you can show the right page referencing the order
	 * when the consumer returns.
	 *
	 * @param redirectUrl the URL to redirect the consumer to after payment
	 *
	 * @return this object for chaining
	 */
	public R redirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return (R) this;
	}

	/**
	 * Sets the webhook URL, where we will send payment status updates to.
	 *
	 * @param webhookUrl the webhook URL
	 *
	 * @return this object for chaining
	 *
	 * @see <a href="https://www.mollie.com/en/docs/webhook">Mollie realtime status updates documentation</a>
	 */
	public R webhookUrl(String webhookUrl) {
		this.webhookUrl = webhookUrl;
		return (R) this;
	}

	/**
	 * Provides any data you like in JSON notation, and we will save the data alongside the payment. Whenever you fetch
	 * the payment with our API, we'll also include the metadata. You can use up to 1kB of JSON.
	 *
	 * @param metadata metadata to save alongside the payment
	 *
	 * @return this object for chaining
	 */
	public R metadata(Map<String, Object> metadata) {
		this.metadata = metadata;
		return (R) this;
	}

	/**
	 * Allows you to preset the language to be used in the payment screens shown to the consumer. When this parameter is
	 * not provided, the browser language will be used instead (which is usually more accurate). You can use any ISO
	 * 15897 locale, but we provide only translations for the locales below:
	 * <p>
	 * Possible values: {@code de_DE}, {@code en_US}, {@code es_ES}, {@code fr_FR}, {@code nl_BE}, {@code fr_BE}, {@code
	 * nl_NL}
	 *
	 * @param locale the language to use in the payment screens
	 *
	 * @return this object for chaining
	 */
	public R locale(String locale) {
		this.locale = locale;
		return (R) this;
	}

	/**
	 * Enables recurring payments. If set to {@code first}, a first payment for the customer is created, allowing the
	 * customer to agree to automatic recurring charges taking place on their account in the future. If set to {@code
	 * recurring}, the customer's card is charged automatically.
	 * <p>
	 * If the {@code recurringType} parameter is set to {@code recurring}, the {@code redirectUrl} parameter is ignored:
	 * since the payment takes place without customer interaction, a redirect is not needed.
	 *
	 * @param recurringType the type of payment for recurring payments
	 *
	 * @return this object for chaining
	 */
	public R recurringType(RecurringType recurringType) {
		this.recurringType = recurringType != null ? recurringType.toString() : null;
		return (R) this;
	}

	/**
	 * Enables recurring payments. For possible values, see the <a href="https://www.mollie.com/en/docs/recurring">The
	 * Mollie recurring payments documentation</a>.
	 *
	 * @param recurringType the type of payment for recurring payments
	 *
	 * @return this object for chaining
	 *
	 * @apiNote Use this method only if you need to set a value that {@link #recurringType(RecurringType)} does not
	 * support. In that case, please also create an issue in the <a href="https://github.com/stil4m/mollie-api/issues">mollie-api
	 * issue tracker</a>.
	 */
	public R recurringType(String recurringType) {
		this.recurringType = recurringType;
		return (R) this;
	}

	/**
	 * The ID of the customer for whom the payment is being created.
	 *
	 * @param customerId the customer ID
	 *
	 * @return this object for chaining
	 */
	public R customerId(String customerId) {
		this.customerId = customerId;
		return (R) this;
	}

	/**
	 * When creating recurring payments, a specific mandate ID may be supplied to indicate which of the consumer's
	 * accounts should be credited.
	 *
	 * @param mandateId the mandate ID for recurring payments
	 *
	 * @return this object for chaining
	 */
	public R mandateId(String mandateId) {
		this.mandateId = mandateId;
		return (R) this;
	}

	/**
	 * Sets a request parameter.
	 *
	 * @param name the parameter name
	 * @param value the parameter value; must be serializable to JSON by the {@linkplain ObjectMapper} used by the
	 * client
	 *
	 * @return this object for chaining
	 *
	 * @apiNote Use this method only if you need to set a parameter that this library does not support natively. In that
	 * case, please also create an issue in the <a href="https://github.com/stil4m/mollie-api/issues">mollie-api issue
	 * tracker</a>.
	 */
	public R parameter(String name, Object value) {
		if (extraParameters == null) extraParameters = new HashMap<>();
		extraParameters.put(name, value);
		return (R) this;
	}
}
