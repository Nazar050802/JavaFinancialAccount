package cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions;

/**
 * Exception thrown when parsing of a record type fails.
 * This exception indicates that the string attempting to be parsed does not match
 * any valid record type defined in the application.
 */
public class ExceptionParseRecordType extends Exception {
    /**
     * Constructs a new ExceptionParseRecordType with the specified detail message.
     *
     * @param message the detail message.
     */
    public ExceptionParseRecordType(String message) {
        super(message);
    }
}
