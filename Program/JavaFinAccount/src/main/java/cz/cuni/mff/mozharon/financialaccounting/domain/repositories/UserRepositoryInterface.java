package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

public interface UserRepositoryInterface {

    User createUser(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException;
    void setUser(User user);
    User getUser();

}
