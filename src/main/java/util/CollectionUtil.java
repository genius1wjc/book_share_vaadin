package util;

import java.util.Collection;

public final class CollectionUtil {

	private CollectionUtil() {
		// Prevent instantiation of this class
	}

	public static boolean isNullOrEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
}
