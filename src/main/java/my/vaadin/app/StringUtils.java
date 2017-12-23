package my.vaadin.app;

import javax.annotation.Nullable;

public class StringUtils {

	private StringUtils() {
		// Prevent instantiation of this class
	}
	
	public static boolean isNullOrEmpty(@Nullable final String string) {
		return string == null || string.isEmpty();
	}
}
