package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

public class User {
    private String loginName;
    private String password;

    public User(String loginName, String fullName, String password) throws InvalidUserException {
        setLoginName(loginName);
        setPassword(password);
    }

    // Getters
    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setLoginName(String loginName) throws InvalidUserException{
        if(loginName.isEmpty()){
            throw new InvalidUserException("User login name length must be more than 0");
        }
        this.loginName = loginName;
    }

    public void setPassword(String password) throws InvalidUserException{
        if(password.isEmpty()){
            throw new InvalidUserException("User password length must be more than 0");
        }
        this.password = password;
    }
}
