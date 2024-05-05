package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;

import java.security.NoSuchAlgorithmException;

/**
 * Provides business logic functions related to user management in the application.
 */
public class UserService {
    private final UserRepositoryInterface userRepository;

    /**
     * Constructs a new UserService with a given user repository.
     *
     * @param userRepository The user repository interface to be used by this service
     */
    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with hashed login name and password, then returns the user.
     *
     * @param loginName The login name of the user
     * @param password  The password of the user
     * @return The newly created user
     * @throws NoSuchAlgorithmException If a required algorithm for hashing is not available
     * @throws InvalidUserException     If user data is invalid
     */

    public User createUser(String loginName, String password) throws NoSuchAlgorithmException, InvalidUserException {
        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        return this.userRepository.createUser(loginName, hashedLoginName, password, hashedPassword);
    }

    /**
     * Checks user credentials against stored values.
     *
     * @param loginName The login name of the user
     * @param password  The password of the user
     * @return true if credentials match, false otherwise
     * @throws NoSuchAlgorithmException If a required algorithm for hashing is not available
     */
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

    public void setNewPassword(String password) throws InvalidUserException, NoSuchAlgorithmException {
        userRepository.getUser().setPassword(password);
        userRepository.getUser().setHashedPassword(HashingUtility.sha256(password));
    }
}
