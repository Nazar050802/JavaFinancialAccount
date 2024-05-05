package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an operation involving a user fails due to invalid data.
 */
public class InvalidUserException extends Exception {
    /**
     * Constructs an InvalidUserException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidUserException(String message) {
        super(message);
    }
}
