package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

public class MainMenuController {
    ControllerCore controllerCore;

    public MainMenuController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    public String getLoginName() {
        return controllerCore.serviceContainer.getUserService().getLoginName();
    }
}
