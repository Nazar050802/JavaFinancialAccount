package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an invalid date is entered or used in the system.
 */
public class InvalidDateException extends Exception {
    /**
     * Constructs an InvalidDateException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}