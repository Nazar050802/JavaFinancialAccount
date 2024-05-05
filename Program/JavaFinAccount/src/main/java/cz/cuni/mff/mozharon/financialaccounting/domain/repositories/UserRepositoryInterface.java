package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

/**
 * Interface defining the operations for user management within the application.
 * This includes operations for creating, retrieving, and updating user information.
 */
public interface UserRepositoryInterface {

    /**
     * Creates a new user with specified details.
     *
     * @param loginName The login name of the user.
     * @param hashedLoginName The hashed version of the login name.
     * @param password The user's password.
     * @param hashedPassword The hashed version of the password.
     * @return The newly created user.
     * @throws InvalidUserException If the user creation fails due to invalid data.
     */
    User createUser(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException;

    /**
     * Sets the current user of the system.
     *
     * @param user The user to set.
     */
    void setUser(User user);

    /**
     * Retrieves the current user of the system.
     *
     * @return The currently set user.
     */
    User getUser();

}
