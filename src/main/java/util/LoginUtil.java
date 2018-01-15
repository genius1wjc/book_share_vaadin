package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;

import model.Borrower;
import model.Lender;
import service.BorrowerService;
import service.LenderService;

public final class LoginUtil {

	private static final String SALT = "book-ninja-salt";

	private LoginUtil() {
		// Prevent instantiation of this class
	}

	/**
	 * @return true if we found the user by the username and password.
	 */
	public static boolean validateUsernameAndPassword(@Nonnull String username, @Nonnull String password) {
		String saltedPassword = SALT + password;
		System.out.println(saltedPassword);
		String hashedPassword = generateHash(saltedPassword);
		System.out.println(hashedPassword);
		Borrower borrower = BorrowerService.find(username, hashedPassword);
		if (borrower != null) {
			return true;
		} else {
			Lender lender = LenderService.find(username, hashedPassword);
			if (lender != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return SHA-256 hashing of input string
	 */
	public static String generateHash(@Nonnull final String input) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
