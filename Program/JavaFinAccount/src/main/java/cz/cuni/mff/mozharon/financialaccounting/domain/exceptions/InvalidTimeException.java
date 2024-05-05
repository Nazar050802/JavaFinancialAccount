package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an invalid time is entered or used in the system.
 */
public class InvalidTimeException extends Exception {
    /**
     * Constructs an InvalidTimeException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidTimeException(String message) {
        super(message);
    }
}