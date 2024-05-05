package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.LoginController;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Handles the login process in the text-based user interface.
 */
public class TUILogin {
    private static final Scanner scanner = new Scanner(System.in);
    private LoginController loginController;

    /**
     * Constructs a TUILogin object with a specified login controller.
     * @param loginController The controller used to manage login operations.
     */
    public TUILogin(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Displays the login prompt and processes the user input for logging in.
     * @throws NoSuchAlgorithmException If a cryptographic algorithm is requested but is not available.
     * @throws InvalidUserException If the user's input is invalid.
     */
    public void loginMessage() throws NoSuchAlgorithmException, InvalidUserException {
        System.out.println("+-----------------------------------+");
        System.out.println("|         Login to the System       |");
        System.out.println("+-----------------------------------+\n");
        System.out.println("+-----------------------------------+");
        System.out.println("| If you are using this application |");
        System.out.println("| for the first time, enter your    |");
        System.out.println("| username and password, which will |");
        System.out.println("| be used to log in.                |");
        System.out.println("+-----------------------------------+\n");

        String username = promptForUsername();
        String password = promptForPassword();

        boolean currentStatusOfLoginCorrectness = true;
        if (!loginController.attemptLogin(username, password)) {
            currentStatusOfLoginCorrectness = false;
            incorrectLogin();
        }
        else {
            loginController.updateUserLoginNameAndPassword(username, password);
            loginController.saveData();
        }

        if(currentStatusOfLoginCorrectness) {
            printLoginInfo(username);
        }
    }

    /**
     * Displays the login information upon successful login.
     * @param username The username of the user who logged in.
     */
    private static void printLoginInfo(String username) {
        System.out.println("\n+-----------------------------------+");
        System.out.println("|        Login Information          |");
        System.out.println("+-----------------------------------+");
        System.out.println("Username: " + username);
        System.out.println("Password: [protected]");
    }

    /**
     * Handles the incorrect login attempt by notifying the user and repeating the login process.
     * @throws NoSuchAlgorithmException If a cryptographic algorithm is requested but is not available.
     * @throws InvalidUserException If the user's input is invalid.
     */
    private void incorrectLogin() throws NoSuchAlgorithmException, InvalidUserException {
        System.out.println("+-----------------------------------+");
        System.out.println("|      Failed to Login Try Again    |");
        System.out.println("+-----------------------------------+\n");

        TUICore.makePause(TUICore.TIME_SHOW_FailedLogin);
        TUIClearConsole.clearConsole();

        loginMessage();
    }

    /**
     * Prompts the user for a username.
     * @return The entered username.
     */
    private static String promptForUsername() {
        System.out.print("Enter your username: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user for a password.
     * @return The entered password.
     */
    private static String promptForPassword() {

        System.out.print("Enter your password: ");
        return scanner.nextLine();

    }
}
