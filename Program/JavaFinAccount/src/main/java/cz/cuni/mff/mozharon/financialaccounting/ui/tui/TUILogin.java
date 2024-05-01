package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.LoginController;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static cz.cuni.mff.mozharon.financialaccounting.ui.tui.TUICore.makePause;

public class TUILogin {
    private static final Scanner scanner = new Scanner(System.in);
    private LoginController loginController;

    public TUILogin(LoginController loginController) {
        this.loginController = loginController;
    }

    public void loginMessage() throws NoSuchAlgorithmException, InvalidUserException {
        System.out.println("+-----------------------------------+");
        System.out.println("|         Login to the System       |");
        System.out.println("+-----------------------------------+\n");

        String username = promptForUsername();
        String password = promptForPassword();

        if (!loginController.attemptLogin(username, password)) {
            incorrectLogin();
        }
        else {
            loginController.updateUserLoginNameAndPassword(username, password);
            loginController.saveData();
        }

        printLoginInfo(username);
    }

    private static void printLoginInfo(String username) {
        System.out.println("\n+-----------------------------------+");
        System.out.println("|        Login Information          |");
        System.out.println("+-----------------------------------+");
        System.out.println("Username: " + username);
        System.out.println("Password: [protected]");
    }

    private void incorrectLogin() throws NoSuchAlgorithmException, InvalidUserException {
        System.out.println("+-----------------------------------+");
        System.out.println("|       Failed Login Try Again      |");
        System.out.println("+-----------------------------------+\n");

        makePause(TUICore.TIME_SHOW_FailedLogin);
        TUIClearConsole.clearConsole();

        loginMessage();
    }

    private static String promptForUsername() {
        System.out.print("Enter your username: ");
        return scanner.nextLine();
    }

    private static String promptForPassword() {
        // Attempt to use Console for password input
        if (System.console() != null) {
            return new String(System.console().readPassword("Enter your password: "));
        } else {
            System.out.print("Enter your password (IDE mode, input may be visible): ");
            return scanner.nextLine();
        }
    }
}
