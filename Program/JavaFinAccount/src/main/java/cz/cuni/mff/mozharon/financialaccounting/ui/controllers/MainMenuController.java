package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

/**
 * Controller responsible for managing the main menu of the application.
 */
public class MainMenuController {
    ControllerCore controllerCore;

    /**
     * Constructs a new MainMenuController with a reference to the core controller.
     *
     * @param controllerCore The core controller with access to the service layer.
     */
    public MainMenuController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Retrieves the login name of the currently authenticated user.
     *
     * @return The login name of the current user.
     */
    public String getLoginName() {
        return controllerCore.serviceContainer.getUserService().getLoginName();
    }
}
