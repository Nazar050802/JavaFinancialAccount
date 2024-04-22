package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;

import java.security.NoSuchAlgorithmException;

public class UserService {
    public User initializeUser(String loginName, String password) throws NoSuchAlgorithmException, InvalidUserException {

        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        return new User(loginName, hashedLoginName, password, hashedPassword);
    }

    public boolean checkLogin(User user, String loginName, String password) throws NoSuchAlgorithmException {

        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        return user.getHashedLoginName().equals(hashedLoginName) && user.getHashedPassword().equals(hashedPassword);
    }
}
