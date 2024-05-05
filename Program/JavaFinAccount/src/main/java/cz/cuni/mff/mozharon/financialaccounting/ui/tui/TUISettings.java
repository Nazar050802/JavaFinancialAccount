package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.SettingsController;

import java.util.Scanner;

import static cz.cuni.mff.mozharon.financialaccounting.ui.tui.TUICore.makePause;

/**
 * Text-based user interface component for managing application settings.
 */
public class TUISettings {

    private SettingsController settingsController;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a TUISettings instance with a specified settings controller.
     *
     * @param settingsController The controller used for managing user settings.
     */
    public TUISettings(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    /**
     * Displays the settings menu and handles user input to navigate various settings actions.
     */
    public void displaySettingsMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Change Password");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose an option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        TUIClearConsole.clearConsole();
                        changePassword();
                        TUIClearConsole.clearConsole();
                        return;
                    case 2:
                        TUIClearConsole.clearConsole();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Facilitates the process of changing a user's password.
     */
    private void changePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (!settingsController.validateCurrentPassword(currentPassword)) {
            System.out.println("Failed to change password. Incorrect current password.");
        } else {
            String newPassword = promptForPassword();
            if (newPassword == null || newPassword.trim().isEmpty()) {
                System.out.println("Password cannot be empty.");
            } else if (settingsController.changePassword(newPassword)) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Failed to change password.");
            }
        }

        TUICore.makePause(TUICore.TIME_SHOW_PASSWORD_CHANGE);
    }

    /**
     * Prompts the user to enter a password, supporting hidden input in compatible environments.
     *
     * @return A string representing the password entered by the user.
     */
    private String promptForPassword() {

        if (System.console() != null) {
            return new String(System.console().readPassword("Enter your new password: "));
        } else {
            System.out.print("Enter your new password (IDE mode, input may be visible): ");
            return scanner.nextLine();
        }

    }
}
