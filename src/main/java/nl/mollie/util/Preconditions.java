package nl.mollie.util;

import javax.annotation.Nullable;

public class Preconditions {
	public static void checkArgument(boolean expression) {
		if (!expression) throw new IllegalArgumentException();
	}

	public static void checkArgument(boolean expression, String errorMessage) {
		if (!expression) throw new IllegalArgumentException(errorMessage);
	}

	public static void checkArgument(boolean expression, String errorMessageFormat, @Nullable Object... errorMessageArgs) {
		if (!expression) throw new IllegalArgumentException(String.format(errorMessageFormat, errorMessageArgs));
	}

	public static <T> T checkNotNull(@Nullable T reference) {
		if (reference == null) throw new NullPointerException();
		return reference;
	}

	public static <T> T checkNotNull(@Nullable T reference, String errorMessage) {
		if (reference == null) throw new NullPointerException(errorMessage);
		return reference;
	}

	public static <T> T checkNotNull(@Nullable T reference, String errorMessageFormat, @Nullable Object... errorMessageArgs) {
		if (reference == null) throw new NullPointerException(String.format(errorMessageFormat, errorMessageArgs));
		return reference;
	}

	public static <T extends CharSequence> T checkNotEmpty(@Nullable T reference) {
		if (reference == null || reference.length() == 0) throw new NullPointerException();
		return reference;
	}

	public static <T extends CharSequence> T checkNotEmpty(@Nullable T reference, String errorMessage) {
		if (reference == null || reference.length() == 0) throw new NullPointerException(errorMessage);
		return reference;
	}

	public static <T extends CharSequence> T checkNotEmpty(@Nullable T reference, String errorMessageFormat, @Nullable Object... errorMessageArgs) {
		if (reference == null || reference.length() == 0) throw new NullPointerException(String.format(errorMessageFormat, errorMessageArgs));
		return reference;
	}

	public static void checkState(boolean expression) {
		if (!expression) throw new IllegalStateException();
	}

	public static void checkState(boolean expression, String errorMessage) {
		if (!expression) throw new IllegalStateException(errorMessage);
	}

	public static void checkState(boolean expression, String errorMessageFormat, Object... errorMessageArgs) {
		if (!expression) throw new IllegalStateException(String.format(errorMessageFormat, errorMessageArgs));
	}
}
