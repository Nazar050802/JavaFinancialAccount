package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.AddRecordController;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.ControllerCore;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.LoginController;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.MainMenuController;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TUICore {
    final static int TIME_SHOW_TUIHelloMessage = 3;
    final static int TIME_SHOW_FailedLogin = 2;
    final static int TIME_SHOW_SuccessLogin = 2;
    final static int TIME_SHOW_NEW_RECORD = 2;
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidUserException, InvalidCategoryException, InvalidAmountException {

        TUIHelloMessage.main(args);

        // Show hello message for 3 seconds
        makePause(TIME_SHOW_TUIHelloMessage);

        TUIClearConsole.clearConsole();

        ControllerCore controllerCore = new ControllerCore();
        LoginController loginController = new LoginController(controllerCore);

        TUILogin tuiLogin = new TUILogin(loginController);
        tuiLogin.loginMessage();

        makePause(TIME_SHOW_SuccessLogin);
        TUIClearConsole.clearConsole();

        MainMenuController mainMenuController = new MainMenuController(controllerCore);
        AddRecordController addRecordController = new AddRecordController(controllerCore);

        TUIMainMenu tuiMainMenu = new TUIMainMenu(mainMenuController, addRecordController);
        tuiMainMenu.mainMenu();
    }

    public static void makePause(int time) {
        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
            Runnable taskTUIHelloMessage = () -> {};

            scheduler.schedule(taskTUIHelloMessage, time, TimeUnit.SECONDS);

            scheduler.shutdown();
        }
    }
}
