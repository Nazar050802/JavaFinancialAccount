package cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;

/**
 * Utility class for formatting and parsing the RecordType enum values for external use.
 */
public class RecordTypeFormatter {

    /**
     * Formats a RecordType enum into a string representation for external use.
     *
     * @param recordType The RecordType enum to format.
     * @return The formatted string representation of the RecordType.
     */
    public static String formatForExternalUse(RecordType recordType) {
        switch (recordType){
            case INCOME -> {
               return "INCOME";
            }
            case EXPENSE -> {
                return "EXPENSE";
            }
            case null, default -> {
                return "UNDEFINED";
            }
        }
    }

    /**
     * Parses a string representation of a record type into a RecordType enum.
     *
     * @param inputStringRecordType The string representation to parse.
     * @return The corresponding RecordType enum.
     * @throws ExceptionParseRecordType if the input string does not correspond to any known RecordType.
     */
    public static RecordType parseFormatForExternalUse(String inputStringRecordType) throws ExceptionParseRecordType {
        switch (inputStringRecordType){
            case "INCOME" -> {
                return RecordType.INCOME;
            }
            case "EXPENSE" -> {
                return RecordType.EXPENSE;
            }
            case null, default -> {
                throw new ExceptionParseRecordType("UNDEFINED RecordType");
            }
        }
    }

}
