package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.*;

import java.util.Scanner;

/**
 * Represents the main menu in the text-based user interface.
 */
public class TUIMainMenu {
    private static Scanner scanner = new Scanner(System.in);

    private MainMenuController mainMenuController;
    private AddRecordController addRecordController;
    private ShowRecordsController showRecordsController;
    private ShowStatisticsController showStatisticsController;
    private SettingsController settingsController;

    /**
     * Constructs a TUIMainMenu with controllers for managing its functionality.
     *
     * @param mainMenuController       Controller for main menu operations.
     * @param addRecordController      Controller for adding records.
     * @param showRecordsController    Controller for showing records.
     * @param showStatisticsController Controller for displaying statistics.
     * @param settingsController       Controller for settings operations.
     */
    public TUIMainMenu(MainMenuController mainMenuController,
                       AddRecordController addRecordController,
                       ShowRecordsController showRecordsController,
                       ShowStatisticsController showStatisticsController,
                       SettingsController settingsController) {
        this.mainMenuController = mainMenuController;
        this.addRecordController = addRecordController;
        this.showRecordsController = showRecordsController;
        this.showStatisticsController = showStatisticsController;
        this.settingsController = settingsController;
    }

    /**
     * Displays and manages the main menu user interactions.
     *
     * @throws InvalidCategoryException  If there is an error in category processing.
     * @throws InvalidAmountException    If there is an error in amount processing.
     * @throws InvalidStatisticField     If there is an error in statistic field processing.
     */
    public void mainMenu() throws InvalidCategoryException, InvalidAmountException, InvalidStatisticField {
        String username = mainMenuController.getLoginName();
        displayGreeting(username);

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    showRecords();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 4:
                    settings();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Displays a greeting message to the user.
     *
     * @param username The username of the current user.
     */
    private static void displayGreeting(String username) {
        System.out.println("+-----------------------------------+");
        System.out.printf("| Hello, %-26s |\n", username);
        System.out.println("+-----------------------------------+");
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMenu() {
        String border = "+-----------------------------------+";
        System.out.println(border);
        System.out.println("|              Main Menu            |");
        System.out.println(border);
        System.out.println("| 1. Add Record                     |");
        System.out.println("| 2. Show Records                   |");
        System.out.println("| 3. Show Statistic                 |");
        System.out.println("| 4. Settings                       |");
        System.out.println("| 5. Exit                           |");
        System.out.println(border + "\n");
        System.out.print("| Enter your choice: ");
    }

    /**
     * Gets the choice input from the user.
     *
     * @return The integer representing the user's menu choice.
     */
    private static int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Handles the addition of a record through the TUI.
     *
     * @throws InvalidCategoryException If there is an error in category processing.
     * @throws InvalidAmountException   If there is an error in amount processing.
     */
    private void addRecord() throws InvalidCategoryException, InvalidAmountException {
        TUIClearConsole.clearConsole();

        TUIAddRecord tuiAddRecord = new TUIAddRecord(addRecordController);
        tuiAddRecord.printAddRecord();

    }

    /**
     * Displays records through the TUI.
     */
    private void showRecords() {
        TUIClearConsole.clearConsole();

        TUIShowRecords tuiShowRecords = new TUIShowRecords(showRecordsController);
        tuiShowRecords.showMenu();
    }

    /**
     * Displays statistics through the TUI.
     *
     * @throws InvalidStatisticField If there is an error in statistic field processing.
     */
    private void showStatistics() throws InvalidStatisticField {
        TUIClearConsole.clearConsole();

        TUIShowStatistics tuiShowStatistics = new TUIShowStatistics(showStatisticsController);
        tuiShowStatistics.displayOptions();
    }

    /**
     * Manages settings through the TUI.
     */
    private void settings() {
        TUIClearConsole.clearConsole();

        TUISettings tuiSettings = new TUISettings(settingsController);
        tuiSettings.displaySettingsMenu();
    }
}
