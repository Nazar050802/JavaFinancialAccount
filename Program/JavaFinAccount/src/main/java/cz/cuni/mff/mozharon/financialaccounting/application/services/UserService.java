package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;

import java.security.NoSuchAlgorithmException;

public class UserService {

    private final UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String loginName, String password) throws NoSuchAlgorithmException, InvalidUserException {

        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        return this.userRepository.createUser(loginName, hashedLoginName, password, hashedPassword);
    }

    public boolean checkCredentials(String loginName, String password) throws NoSuchAlgorithmException {

        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        return userRepository.getUser().getHashedLoginName().equals(hashedLoginName) && userRepository.getUser().getHashedPassword().equals(hashedPassword);
    }

    public String getHashedSha1UserLoginName() throws NoSuchAlgorithmException {
        return HashingUtility.sha1(userRepository.getUser().getLoginName());
    }

    public User getUser(){
        return userRepository.getUser();
    }

    public void setUser(User user) {
        userRepository.setUser(user);
    }

    public String getLoginName() {
        return userRepository.getUser().getLoginName();
    }

    public void setLoginName(String loginName) throws InvalidUserException {
        userRepository.getUser().setLoginName(loginName);
    }

    public void setPassword(String password) throws InvalidUserException {
        userRepository.getUser().setPassword(password);
    }
}
