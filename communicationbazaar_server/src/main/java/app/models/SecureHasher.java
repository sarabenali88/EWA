package app.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SecureHasher {
    private static final String SECRET_SALT = "HvA-HBO-ICT-2022";
    private static final ThreadLocal<MessageDigest> messageDigestThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(String.format("%-16s", SECRET_SALT).getBytes(StandardCharsets.UTF_8));
            // Optionally, add more salt or configurations for security
            return md;
        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
            return null;
        }
    });

    /**
     * Calculate a secure hash from a source for the purpose of password hashing.
     *
     * @param source The input string to be hashed
     * @return The hashed result as a Base64-encoded string
     */
    public static String secureHash(String source) {
        MessageDigest messageDigest = messageDigestThreadLocal.get();

        if (messageDigest == null || source == null) {
            return null;
        }

        // Reset the digest to initial state for each new hash computation
        messageDigest.reset();

        // Hash the source using the thread-local salted SHA hasher
        byte[] hashedResult = messageDigest.digest(source.getBytes(StandardCharsets.UTF_8));

        // Convert the hashed result to a Base64-encoded string
        return Base64.getEncoder().encodeToString(hashedResult);
    }
}