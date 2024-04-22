package cz.cuni.mff.mozharon.financialaccounting.application.commands;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

public interface CommandInterface {
    void execute()  throws InvalidAmountException;
}
