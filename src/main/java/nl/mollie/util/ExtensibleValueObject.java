package nl.mollie.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Abstract base class that accepts unknown JSON properties when deserialized.
 */
public abstract class ExtensibleValueObject extends ValueObject {

	@JsonIgnore
	private Map<String, Object> unknownProperties;

	/**
	 * Gets a property that is not available using any of this class' getters.
	 * <p>
	 * This method will first try to use a bean property to preserve backward compatibility in case the property is
	 * added in a later version of the library.
	 *
	 * @param propertyName the property name
	 *
	 * @return the property value; {@code null} if the property does not exist
	 *
	 * @apiNote Use this method only if you need to get a property that this class does not support natively. In that
	 * case, please also create an issue in the <a href="https://github.com/stil4m/mollie-api/issues">mollie-api issue
	 * tracker</a>.
	 */
	@Nullable
	public Object get(String propertyName) {
		// First try getter
		try {
			Method getter = new PropertyDescriptor(propertyName, getClass()).getReadMethod();
			return getter.invoke(this);
		} catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
			// Pass through
		}
		// Then try unknown properties
		return unknownProperties != null ? unknownProperties.get(propertyName) : null;
	}

	@JsonAnySetter
	private void setUnknownProperty(String key, Object value) {
		if (unknownProperties == null) unknownProperties = new HashMap<>();
		unknownProperties.put(key, value);
	}
}
