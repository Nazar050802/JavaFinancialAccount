package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

public class User {
    private String loginName;
    public final static int maxLoginNameLength = 40;
    private String password;
    public final static int maxPasswordLength = 40;
    private String hashedLoginName;
    private String hashedPassword;

    public User(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException {
        setHashedLoginName(hashedLoginName);
        setHashedPassword(hashedPassword);

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
    public String getHashedLoginName() {
        return hashedLoginName;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }

    // Setters
    public void setLoginName(String loginName) throws InvalidUserException{
        if(loginName.isEmpty() && hashedLoginName.isEmpty()){
            throw new InvalidUserException("User login name length must be more than 0");
        }
        if(loginName.length() > maxLoginNameLength){
            throw new InvalidUserException("User login name length must be less than 40 symbols");
        }
        this.loginName = loginName;
    }

    public void setPassword(String password) throws InvalidUserException{
        if(password.isEmpty() && hashedPassword.isEmpty()){
            throw new InvalidUserException("User password length must be more than 0");
        }
        if(password.length() > maxPasswordLength){
            throw new InvalidUserException("User password length must be less than 40 symbols");
        }
        this.password = password;
    }

    public void setHashedLoginName(String hashedLoginName) throws InvalidUserException {
        if(hashedLoginName.isEmpty()){
            throw new InvalidUserException("Hashed user login name length must be more than 0");
        }
        this.hashedLoginName = hashedLoginName;
    }

    public void setHashedPassword(String hashedPassword) throws InvalidUserException {
        if(hashedPassword.isEmpty()){
            throw new InvalidUserException("Hashed user password length must be more than 0");
        }
        this.hashedPassword = hashedPassword;
    }
}
