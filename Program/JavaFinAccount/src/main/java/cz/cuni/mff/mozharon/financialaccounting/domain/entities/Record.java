package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.config.LoggerConfig;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Record {

    private static final Logger logger = LoggerConfig.getLogger(Record.class);

    public enum RecordType {
        INCOME,
        EXPENSE
    }

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) throws InvalidAmountException {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.log(Level.WARNING, "The amount has invalid value. It must be greater than 0.");
            throw new InvalidAmountException("The amount has invalid value. It must be greater than 0.");
        }
        this.amount = amount;

    }

    private String description;
    private DateAndTime dateAndTime;
    private Category category;
    private RecordType recordType;

    public RecordType getRecordType() {
        return recordType;
    }

    public String getDescription() {
        return description;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public Category getCategory() {
        return category;
    }

    public Record(BigDecimal amount, String description, String dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        setAmount(amount);
        this.description = description;
        this.dateAndTime = new DateAndTime(dateAndTime);
        this.category = category;
        this.recordType = recordType;
    }


}
