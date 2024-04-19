package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.DateAndTimeFormatter;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.RecordTypeFormatter;

import java.math.BigDecimal;

public class RecordSerializer {

    public String serialize(Record record) {
        return record.getId() + "|" + record.getAmount() + "|" + record.getDescription() + "|" + DateAndTimeFormatter.formatForExternalUse(record.getDateAndTime()) +
                "|" + record.getCategory() + "|" + RecordTypeFormatter.formatForExternalUse(record.getRecordType());
    }

    // Deserialize a String to a Record object
    public Record deserialize(String data) throws InvalidAmountException, ExceptionParseRecordType {
        String[] parts = data.split("\\|");

        long id = Long.parseLong(parts[0]);
        BigDecimal amount = new BigDecimal(parts[1]);
        String description = parts[2];
        DateAndTime dateAndTime = DateAndTimeFormatter.parseFormatForExternalUse(parts[3]);
        Category category = new Category(parts[4]);
        RecordType recordType = RecordTypeFormatter.parseFormatForExternalUse(parts[5]);

        return new Record(id, amount, description, dateAndTime, category, recordType);
    }

}
