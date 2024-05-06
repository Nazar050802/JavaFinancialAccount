package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

/**
 * Represents a user within the financial accounting application.
 * This class stores user-specific details like login name, password, and their hashed analogs.
 */
public class User {
    private String loginName;
    public final static int maxLoginNameLength = 40;
    private String password;
    public final static int maxPasswordLength = 40;
    private String hashedLoginName;
    private String hashedPassword;

    /**
     * Constructs a new User with specific login and password details.
     *
     * @param loginName       The login name of the user.
     * @param hashedLoginName The hashed version of the login name.
     * @param password        The user's password.
     * @param hashedPassword  The hashed version of the password.
     * @throws InvalidUserException If any of the input parameters do not meet the validation criteria.
     */
    public User(String loginName, String hashedLoginName, String password, String hashedPassword) throws InvalidUserException {
        setHashedLoginName(hashedLoginName);
        setHashedPassword(hashedPassword);

        setLoginName(loginName);
        setPassword(password);
    }

    // Getters

    /**
     * Retrieves the login name of the user.
     *
     * @return The login name as a string.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password as a string.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the hashed version of the user's login name.
     *
     * @return The hashed login name as a string.
     */
    public String getHashedLoginName() {
        return hashedLoginName;
    }

    /**
     * Retrieves the hashed version of the user's password.
     *
     * @return The hashed password as a string.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    // Setters

    /**
     * Sets the login name of the user, ensuring it meets the length requirements.
     *
     * @param loginName The login name to set.
     * @throws InvalidUserException if the login name is empty or exceeds the maximum length.
     */
    public void setLoginName(String loginName) throws InvalidUserException{
        if(loginName.isEmpty() && hashedLoginName.isEmpty()){
            throw new InvalidUserException("User login name length must be more than 0");
        }
        if(loginName.length() > maxLoginNameLength){
            throw new InvalidUserException("User login name length must be less than 40 symbols");
        }
        this.loginName = loginName;
    }

    /**
     * Sets the user's password, ensuring it meets the length requirements.
     *
     * @param password The password to set.
     * @throws InvalidUserException if the password is empty or exceeds the maximum length.
     */
    public void setPassword(String password) throws InvalidUserException{
        if(password.isEmpty() && hashedPassword.isEmpty()){
            throw new InvalidUserException("User password length must be more than 0");
        }
        if(password.length() > maxPasswordLength){
            throw new InvalidUserException("User password length must be less than 40 symbols");
        }
        this.password = password;
    }

    /**
     * Sets the hashed login name of the user.
     *
     * @param hashedLoginName The hashed login name to set.
     * @throws InvalidUserException if the hashed login name is empty.
     */
    public void setHashedLoginName(String hashedLoginName) throws InvalidUserException {
        if(hashedLoginName.isEmpty()){
            throw new InvalidUserException("Hashed user login name length must be more than 0");
        }
        this.hashedLoginName = hashedLoginName;
    }

    /**
     * Sets the hashed password of the user.
     *
     * @param hashedPassword The hashed password to set.
     * @throws InvalidUserException if the hashed password is empty.
     */
    public void setHashedPassword(String hashedPassword) throws InvalidUserException {
        if(hashedPassword.isEmpty()){
            throw new InvalidUserException("Hashed user password length must be more than 0");
        }
        this.hashedPassword = hashedPassword;
    }
}
