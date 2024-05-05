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

/**
 * Serializer for Record objects, implementing the SerializerInterface.
 */
public class RecordSerializer implements SerializerInterface<Record> {
    public static final String keyWord = "RECORD";

    /**
     * Serializes a Record object into a string representation.
     *
     * @param record the record to serialize
     * @return the serialized string representation of the record
     */
    @Override
    public String serialize(Record record) {
        return keyWord + "|"
                + record.getId() + "|"
                + record.getAmount() + "|"
                + SerializerUtils.cleanseField(record.getDescription()) + "|"
                + DateAndTimeFormatter.formatForExternalUse(record.getDateAndTime()) + "|"
                + SerializerUtils.cleanseField(record.getCategory().getName()) + "|"
                + RecordTypeFormatter.formatForExternalUse(record.getRecordType());
    }

    /**
     * Deserializes a string back into a Record object.
     *
     * @param data the string to deserialize
     * @return the deserialized Record object
     * @throws InvalidAmountException if the amount field is invalid
     * @throws InvalidCategoryException if the category field is invalid
     * @throws ExceptionParseRecordType if the record type field is invalid
     */
    @Override
    public Record deserialize(String data) throws InvalidAmountException, InvalidCategoryException, ExceptionParseRecordType {
        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        long id = Long.parseLong(parts[numberToStartWith]);
        Double amount = Double.parseDouble(parts[numberToStartWith + 1]);
        String description = parts[numberToStartWith + 2];
        DateAndTime dateAndTime = DateAndTimeFormatter.parseFormatForExternalUse(parts[numberToStartWith + 3]);

        Category category = new Category(parts[numberToStartWith + 4]);
        RecordType recordType = RecordTypeFormatter.parseFormatForExternalUse(parts[numberToStartWith + 5]);

        return new Record(id, amount, description, dateAndTime, category, recordType);
    }
}
