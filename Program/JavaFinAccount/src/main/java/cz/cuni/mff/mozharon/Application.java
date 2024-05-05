package cz.cuni.mff.mozharon;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.ui.tui.TUICore;

import java.security.NoSuchAlgorithmException;

/**
 * The main class for the Financial Accounting application.
 * This class is responsible for initializing and starting the application.
 */
public class Application {
    /**
     * Main method that serves as the entry point for the application.
     * Initializes the core components and starts the user interface.
     *
     * @param args The command line arguments
     * @throws InvalidAmountException     If an invalid amount is detected during initialization
     * @throws InvalidStatisticField      If an invalid statistic field is used during initialization
     * @throws InvalidCategoryException   If an invalid category is provided during initialization
     * @throws InvalidUserException       If an invalid user configuration is encountered during initialization
     * @throws NoSuchAlgorithmException   If a required algorithm for security measures is not available
     */
    public static void main(String[] args) throws  InvalidAmountException, InvalidStatisticField, InvalidCategoryException, InvalidUserException, NoSuchAlgorithmException {

        TUICore.main(new String[]{});

    }

}

