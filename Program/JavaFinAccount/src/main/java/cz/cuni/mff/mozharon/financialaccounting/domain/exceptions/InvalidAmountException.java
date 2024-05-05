package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an invalid amount is specified in a financial transaction.
 */
public class InvalidAmountException extends Exception {
    /**
     * Constructs an InvalidAmountException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidAmountException(String message) {
        super(message);
    }
}
