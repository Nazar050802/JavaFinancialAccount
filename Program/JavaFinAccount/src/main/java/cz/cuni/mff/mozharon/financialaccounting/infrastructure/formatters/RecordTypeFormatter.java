package cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;

public class RecordTypeFormatter {

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
