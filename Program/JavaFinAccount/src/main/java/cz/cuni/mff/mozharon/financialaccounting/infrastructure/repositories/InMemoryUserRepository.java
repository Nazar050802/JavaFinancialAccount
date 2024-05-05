package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;

/**
 * In-memory storage implementation of the UserRepositoryInterface for managing user data.
 */
public class InMemoryUserRepository implements UserRepositoryInterface {
    private User user;

    /**
     * Constructs an InMemoryUserRepository with no initial user data.
     */
    public InMemoryUserRepository() {
        this.user = null;
    }

    /**
     * Creates a user and stores it in memory.
     *
     * @param loginName the user's login name
     * @param hashedLoginName the hashed version of the user's login name
     * @param password the user's password
     * @param hashedPassword the hashed version of the user's password
     * @return the newly created user object
     * @throws InvalidUserException if any of the user creation parameters are invalid
     */
    @Override
    public User createUser(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException {
        this.user = new User(loginName, hashedLoginName, password, hashedPassword);
        return this.user;
    }

    /**
     * Sets the current user in memory.
     *
     * @param user the user to set
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Retrieves the current user stored in memory.
     *
     * @return the currently stored user
     */
    @Override
    public User getUser() {
        return this.user;
    }
}
