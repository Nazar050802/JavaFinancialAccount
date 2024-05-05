package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an invalid category name is used or manipulated.
 */
public class InvalidCategoryException extends Exception {
    /**
     * Constructs an InvalidCategoryException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidCategoryException(String message) {
        super(message);
    }
}
