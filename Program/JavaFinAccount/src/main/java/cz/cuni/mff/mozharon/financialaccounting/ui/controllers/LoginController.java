package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

import java.security.NoSuchAlgorithmException;

/**
 * Controller responsible for handling user login processes.
 */
public class LoginController {

    ControllerCore controllerCore;

    /**
     * Constructs a new LoginController.
     *
     * @param controllerCore The core controller with access to the service layer.
     */
    public LoginController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Attempts to authenticate a user with the provided login credentials.
     *
     * @param loginName The username of the user attempting to log in.
     * @param password The password of the user.
     * @return true if the login was successful, false otherwise.
     * @throws NoSuchAlgorithmException If the cryptographic algorithm is not available.
     * @throws InvalidUserException If the user details are invalid.
     */
    public boolean attemptLogin(String loginName, String password) throws NoSuchAlgorithmException, InvalidUserException {
        controllerCore.serviceContainer.getUserService().createUser(loginName, password);

        return controllerCore.tryToReadDataFromFile();
    }

    /**
     * Updates the stored login credentials for the user.
     *
     * @param loginName The new login name for the user.
     * @param password The new password for the user.
     * @throws InvalidUserException If the user details are invalid.
     */
    public void updateUserLoginNameAndPassword(String loginName, String password) throws InvalidUserException {
        controllerCore.serviceContainer.getUserService().setLoginName(loginName);
        controllerCore.serviceContainer.getUserService().setPassword(password);
    }

    /**
     * Saves the current application state to a file.
     *
     * @return true if the data was successfully saved, false if an error occurred during saving.
     */
    public boolean saveData(){
        try {
            controllerCore.tryToSaveDataToFile();
        }
        catch (Exception e){
            return false;
        }

        return true;
    }
}
