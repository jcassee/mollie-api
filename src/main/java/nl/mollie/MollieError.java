package nl.mollie;

import static java.util.Collections.emptyMap;

import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonRootName;

import nl.mollie.util.ExtensibleValueObject;

/**
 * An error returned by the Mollie API.
 *
 * @see <a href="https://www.mollie.com/en/docs/errors">Mollie documentation on errors</a>
 */
@JsonRootName("error")
@SuppressWarnings("unused")
public class MollieError extends ExtensibleValueObject {

	private String type;
	private String message;
	private String field;
	private Map<String, String> links;

	/**
	 * Gets the error type, for example {@code request} if there was a problem with the request.
	 *
	 * @return the error type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets a human readable error message.
	 *
	 * @return the error message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the part or field of the request that is likely to have caused the error.
	 * <p>
	 * This information is often present when the response status is 422 Unprocessable Entity.
	 *
	 * @return the request part or field that is likely to have caused the error
	 */
	@Nullable
	public String getField() {
		return field;
	}

	/**
	 * Get any links returned in the error.
	 * <p>
	 * If no links were returned, this method returns an empty map.
	 *
	 * @return the error links; never {@code null}.
	 */
	public Map<String, String> getLinks() {
		return links != null ? links : emptyMap();
	}

	/**
	 * Gets a specific link if it was returned in the error.
	 *
	 * @param key the key in the link map
	 *
	 * @return the link URL; {@code null} if so such link was returned
	 */
	@Nullable
	public String getLink(String key) {
		return links != null ? links.get(key) : null;
	}

	static class Wrapper {
		private MollieError error;

		public MollieError getError() {
			return error;
		}
	}
}
