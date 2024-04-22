package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;

import java.security.NoSuchAlgorithmException;

public class UserSerializer implements SerializerInterface<User> {

    @Override
    public String serialize(User user) throws NoSuchAlgorithmException {
        return "USER" + "|" + HashingUtility.sha256(user.getLoginName()) + "|" + HashingUtility.sha256(user.getPassword());
    }

    @Override
    public User deserialize(String data) throws InvalidUserException {

        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        return new User("", parts[numberToStartWith], "", parts[numberToStartWith + 1]);
    }

}
