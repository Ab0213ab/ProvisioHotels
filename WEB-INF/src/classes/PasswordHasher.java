/**
 * Generate hashes and salts
 */
package Provisio;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.Base64.Encoder;
import java.util.Base64;

class PasswordHasher {
	/**
	 * Generate n character crypto-safe random salt
	 */
	public static String generateSalt(
		Integer length
	){
		// Adjust length to ensure it is what they wanted:
		length -= 8;

		SecureRandom randomizer = new SecureRandom();
		byte bytes[] = new byte[length];

		// Generate random bytes:
		randomizer.nextBytes(bytes);

		// Encode into string:
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		String salt = encoder.encodeToString(bytes);

		return salt;
	}

	/**
	 * Hash a password with a given salt (at the end)
	 * i.e. "password" + salt
	 */
	public static String hash(
		String password,
		String salt
	) throws NoSuchAlgorithmException {
		MessageDigest messageDigester = MessageDigest.getInstance("SHA-256");

		// Load in password + salt:
		messageDigester.update((password + salt).getBytes(StandardCharsets.UTF_8));

		// Perform the hash:
		byte hashedBytes[] = messageDigester.digest();

		// Convert to string and return:
		BigInteger bigIntegerRepresentationOfHashedBytes = new BigInteger(1, hashedBytes);
		String hexRepresentationOfHashedBytes = String.format("%064x", bigIntegerRepresentationOfHashedBytes);

		return hexRepresentationOfHashedBytes;
	}
}