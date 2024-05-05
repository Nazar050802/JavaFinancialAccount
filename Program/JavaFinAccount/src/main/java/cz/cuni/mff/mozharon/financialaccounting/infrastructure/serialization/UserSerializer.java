package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;

import java.security.NoSuchAlgorithmException;

/**
 * Serializer for User objects, implementing the SerializerInterface.
 */
public class UserSerializer implements SerializerInterface<User> {
    public static final String keyWord = "USER";

    /**
     * Serializes a User object into a string with hashed login and password.
     *
     * @param user the user to serialize
     * @return a string representation of the user
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available
     */
    @Override
    public String serialize(User user) throws NoSuchAlgorithmException {
        return keyWord + "|" + HashingUtility.sha256(user.getLoginName()) + "|" + HashingUtility.sha256(user.getPassword());
    }

    /**
     * Deserializes a string back into a User object.
     *
     * @param data the string to deserialize
     * @return the deserialized User object
     * @throws InvalidUserException if the user data is invalid
     */
    @Override
    public User deserialize(String data) throws InvalidUserException {

        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        return new User("", parts[numberToStartWith], "", parts[numberToStartWith + 1]);
    }

}
