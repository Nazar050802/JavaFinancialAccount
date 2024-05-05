package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

/**
 * Exception thrown when an invalid statistic field is encountered during data processing.
 */
public class InvalidStatisticField extends Exception{
    /**
     * Constructs an InvalidStatisticField with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidStatisticField(String message) {
        super(message);
    }

}
