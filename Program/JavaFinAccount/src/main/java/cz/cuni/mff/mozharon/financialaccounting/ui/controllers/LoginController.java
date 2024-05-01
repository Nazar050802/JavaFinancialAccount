package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;

import java.security.NoSuchAlgorithmException;

public class LoginController {

    ControllerCore controllerCore;

    public LoginController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    public boolean attemptLogin(String loginName, String password) throws NoSuchAlgorithmException, InvalidUserException {
        controllerCore.serviceContainer.getUserService().createUser(loginName, password);

        return controllerCore.tryToReadDataFromFile();
    }

    public void updateUserLoginNameAndPassword(String loginName, String password) throws InvalidUserException {
        controllerCore.serviceContainer.getUserService().setLoginName(loginName);
        controllerCore.serviceContainer.getUserService().setPassword(password);
    }

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
