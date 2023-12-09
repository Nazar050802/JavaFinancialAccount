package cz.cuni.mff.mozharon.financialaccounting.domain.exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
