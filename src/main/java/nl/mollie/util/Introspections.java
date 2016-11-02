package nl.mollie.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nullable;

public class Introspections {

	@Nullable
	public Object getProperty(Object instance, String propertyName) {
		try {
			Method getter = new PropertyDescriptor(propertyName, instance.getClass()).getReadMethod();
			return getter.invoke(instance);
		} catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
			return null;
		}
	}
}
