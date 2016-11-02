package nl.mollie.util;

import javax.annotation.Nullable;

public class Strings {
	public static boolean isNullOrEmpty(@Nullable String string) {
		return string == null || string.isEmpty();
	}

	public static String trim(String value, char c) {
		int len = value.length();
		int st = 0;

		while ((st < len) && (value.charAt(st) == c)) {
			st++;
		}
		while ((st < len) && (value.charAt(len - 1) == c)) {
			len--;
		}
		return ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;
	}
}
