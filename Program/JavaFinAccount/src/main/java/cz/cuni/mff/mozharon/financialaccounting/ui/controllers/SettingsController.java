package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

import java.security.NoSuchAlgorithmException;

/**
 * Controller for handling settings functionality within the application.
 */
public class SettingsController {

    ControllerCore controllerCore;

    /**
     * Constructs a SettingsController with a reference to the central controller.
     * @param controllerCore The core controller containing service configurations.
     */
    public  SettingsController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Validates the user's current password against the stored password.
     * @param currentPassword The password to validate.
     * @return true if the password is correct, false otherwise.
     */
    public boolean validateCurrentPassword(String currentPassword) {
        try {
            if (controllerCore.serviceContainer.getUserService().checkCredentials(controllerCore.serviceContainer.getUserService().getLoginName(), currentPassword)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Changes the user's password to a new one after validating the current password.
     * @param newPassword The new password to set.
     * @return true if the password was successfully changed, false otherwise.
     */
    public boolean changePassword(String newPassword) {
        try {
            controllerCore.serviceContainer.getUserService().setNewPassword(newPassword);
            controllerCore.tryToSaveDataToFile();

            return true;

        } catch (NoSuchAlgorithmException | InvalidUserException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

}
