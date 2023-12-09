package cz.cuni.mff.mozharon.financialaccounting.domain;

import cz.cuni.mff.mozharon.financialaccounting.config.LoggerConfig;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidDateException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Record {

    private static final Logger logger = LoggerConfig.getLogger(Record.class);

    private double amount;

    private void setAmount(int amount) throws InvalidAmountException {

        if (amount <= 0) {
            logger.log(Level.WARNING, "The amount has invalid value. It must be greater than 0.");
            throw new InvalidAmountException("The amount has invalid value. It must be greater than 0.");
        }
        this.amount = amount;

    }

    private String description;
    private DateAndTime dateAndTime;
    private Category category;

    public Record() {
        
    }


}
