package nl.mollie.util;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoDetectPolicy;
import org.pojomatic.annotations.AutoProperty;

/**
 * Abstract base class for value objects. Implements {@linkplain #equals}, {@linkplain #hashCode} and {@linkplain
 * #toString} based on fields (using {@link Pojomatic});
 */
@AutoProperty(autoDetect = AutoDetectPolicy.METHOD)
public abstract class ValueObject {
	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public final boolean equals(Object other) {
		return Pojomatic.equals(this, other);
	}

	@Override
	public final int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public final String toString() {
		return Pojomatic.toString(this);
	}
}
