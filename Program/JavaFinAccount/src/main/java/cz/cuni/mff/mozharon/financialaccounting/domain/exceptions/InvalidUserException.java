package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
