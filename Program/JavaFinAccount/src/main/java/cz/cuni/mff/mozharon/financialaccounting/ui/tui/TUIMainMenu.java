package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.AddRecordController;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.MainMenuController;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.ShowRecordsController;

import java.util.Scanner;

public class TUIMainMenu {
    private static Scanner scanner = new Scanner(System.in);

    private MainMenuController mainMenuController;
    private AddRecordController addRecordController;
    private ShowRecordsController showRecordsController;

    public TUIMainMenu(MainMenuController mainMenuController, AddRecordController addRecordController, ShowRecordsController showRecordsController) {
        this.mainMenuController = mainMenuController;
        this.addRecordController = addRecordController;
        this.showRecordsController = showRecordsController;
    }

    public void mainMenu() throws InvalidCategoryException, InvalidAmountException {
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

    private static void displayGreeting(String username) {
        System.out.println("+-----------------------------------+");
        System.out.printf("| Hello, %-26s |\n", username);
        System.out.println("+-----------------------------------+");
    }

    private static void displayMenu() {
        String border = "+-----------------------------------+";
        System.out.println(border);
        System.out.println("|              Main Menu            |");
        System.out.println(border);
        System.out.println("| 1. Add Record                     |");
        System.out.println("| 2. Show Records                   |");
        System.out.println("| 3. Show Statistics                |");
        System.out.println("| 4. Settings                       |");
        System.out.println("| 5. Exit                           |");
        System.out.println(border + "\n");
        System.out.print("| Enter your choice: ");
    }

    private static int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left behind by nextInt
        return choice;
    }

    private void addRecord() throws InvalidCategoryException, InvalidAmountException {
        TUIClearConsole.clearConsole();

        TUIAddRecord tuiAddRecord = new TUIAddRecord(addRecordController);
        tuiAddRecord.printAddRecord();

    }

    private void showRecords() {
        TUIClearConsole.clearConsole();

        TUIShowRecords tuiShowRecords = new TUIShowRecords(showRecordsController);
        tuiShowRecords.showMenu();
    }

    private static void showStatistics() {
        System.out.println("Function to display statistics.");
        // Implementation of displaying statistics
    }

    private static void settings() {
        System.out.println("Function to modify settings.");
        // Implementation of modifying settings
    }
}
