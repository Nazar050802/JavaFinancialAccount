package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}