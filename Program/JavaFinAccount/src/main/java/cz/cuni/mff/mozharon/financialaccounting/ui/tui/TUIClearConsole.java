package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

/**
 * Provides utility methods for clearing the console screen in a text-based user interface.
 */
public class TUIClearConsole {
    private final static int NUMBER_OF_LINES_TO_CLEAR = 100;

    /**
     * Clears the console by printing a series of newline characters.
     */
    public static void clearConsole() {
        for (int i = 0; i < NUMBER_OF_LINES_TO_CLEAR; i++) {
            System.out.println("\n");
        }
    }

}
