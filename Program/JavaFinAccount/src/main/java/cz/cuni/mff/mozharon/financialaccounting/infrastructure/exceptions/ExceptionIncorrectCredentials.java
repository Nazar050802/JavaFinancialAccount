package cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions;

/**
 * Exception thrown when provided user credentials are incorrect.
 * This exception is used to signal that an authentication or verification process
 * for user login has failed due to invalid credentials.
 */
public class ExceptionIncorrectCredentials extends Exception {
    /**
     * Constructs a new ExceptionIncorrectCredentials with the specified detail message.
     *
     * @param message the detail message.
     */
    public ExceptionIncorrectCredentials(String message) {
        super(message);
    }
}
