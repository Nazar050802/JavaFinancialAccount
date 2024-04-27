package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;

public class InMemoryUserRepository implements UserRepositoryInterface {
    private User user;

    public InMemoryUserRepository() {
        this.user = null;
    }

    @Override
    public User createUser(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException {
        this.user = new User(loginName, hashedLoginName, password, hashedPassword);
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
