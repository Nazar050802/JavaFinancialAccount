package cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class providing static methods for hashing strings using different algorithms.
 */
public class HashingUtility {

    /**
     * Converts a byte array into a hex string.
     *
     * @param hash the byte array to convert
     * @return the resulting hex string
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available
     */
    private static String toHexString(byte[] hash) throws NoSuchAlgorithmException {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hashes a string using SHA-256 and returns the hex string.
     *
     * @param input the string to hash
     * @return the hashed string in hex format
     */
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return toHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Hashes a string using SHA-1 and returns the hex string.
     *
     * @param input the string to hash
     * @return the hashed string in hex format
     */
    public static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return toHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }

}
