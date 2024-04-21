package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserSerializer implements SerializerInterface<User> {

    @Override
    public String serialize(User user) throws NoSuchAlgorithmException {
        return "USER" + "|" + sha256(user.getLoginName()) + "|" + sha256(user.getPassword());
    }

    @Override
    public User deserialize(String data) throws InvalidUserException {
        return null;
    }

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

    private static String sha256(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return toHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
