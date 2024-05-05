package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.*;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main class for text user interface core functionality.
 * It initializes controllers and manages the main execution flow of the application.
 */
public class TUICore extends TUIHelloMessage {
    final static int TIME_SHOW_TUIHelloMessage = 3;
    final static int TIME_SHOW_FailedLogin = 2;
    final static int TIME_SHOW_SuccessLogin = 2;
    final static int TIME_SHOW_NEW_RECORD = 2;
    final static int TIME_SHOW_RECORD_DELETED = 2;
    final static int TIME_SHOW_PASSWORD_CHANGE = 2;

    public static void startCore() throws NoSuchAlgorithmException, InvalidUserException, InvalidCategoryException, InvalidAmountException, InvalidStatisticField {

        TUIHelloMessage.showHelloMessage();

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
        ShowRecordsController showRecordsController = new ShowRecordsController(controllerCore);
        ShowStatisticsController showStatisticsController = new ShowStatisticsController(controllerCore);
        SettingsController settingsController = new SettingsController(controllerCore);

        TUIMainMenu tuiMainMenu = new TUIMainMenu(
                mainMenuController,
                addRecordController,
                showRecordsController,
                showStatisticsController,
                settingsController);

        tuiMainMenu.mainMenu();
    }

    /**
     * Pauses the program execution for a given amount of time.
     *
     * @param time The number of seconds to pause.
     */
    public static void makePause(int time) {
        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
            Runnable taskTUIHelloMessage = () -> {};

            scheduler.schedule(taskTUIHelloMessage, time, TimeUnit.SECONDS);

            scheduler.shutdown();
        }
    }
}
