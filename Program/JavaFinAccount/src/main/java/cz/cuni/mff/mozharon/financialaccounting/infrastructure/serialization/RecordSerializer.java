package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.DateAndTimeFormatter;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.RecordTypeFormatter;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.SerializerUtils;

import java.math.BigDecimal;

public class RecordSerializer implements SerializerInterface<Record> {
    public String keyWord = "RECORD";

    @Override
    public String serialize(Record record) {
        return "RECORD" + "|"
                + record.getId() + "|"
                + record.getAmount() + "|"
                + SerializerUtils.cleanseField(record.getDescription()) + "|"
                + DateAndTimeFormatter.formatForExternalUse(record.getDateAndTime()) + "|"
                + SerializerUtils.cleanseField(record.getCategory().getName()) + "|"
                + RecordTypeFormatter.formatForExternalUse(record.getRecordType());
    }

    // Deserialize a String to a Record object
    @Override
    public Record deserialize(String data) throws InvalidAmountException, ExceptionParseRecordType, InvalidCategoryException {
        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        long id = Long.parseLong(parts[numberToStartWith]);
        BigDecimal amount = new BigDecimal(parts[numberToStartWith + 1]);
        String description = parts[numberToStartWith + 2];
        DateAndTime dateAndTime = DateAndTimeFormatter.parseFormatForExternalUse(parts[numberToStartWith + 3]);
        Category category = new Category(parts[numberToStartWith + 4]);
        RecordType recordType = RecordTypeFormatter.parseFormatForExternalUse(parts[numberToStartWith + 5]);

        return new Record(id, amount, description, dateAndTime, category, recordType);
    }
}
