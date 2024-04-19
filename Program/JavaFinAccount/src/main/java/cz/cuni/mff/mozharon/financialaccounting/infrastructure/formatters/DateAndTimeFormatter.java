package cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;

public class DateAndTimeFormatter {
    public static String formatForExternalUse(DateAndTime dateAndTime) {
        String output = "";

        output += dateAndTime.getDay() + " ";
        output += dateAndTime.getMonth() + " ";
        output += dateAndTime.getYear() + " ";
        output += dateAndTime.getSeconds() + " ";
        output += dateAndTime.getMinutes() + " ";
        output += dateAndTime.getHours();

        return output;
    }

    public static DateAndTime parseFormatForExternalUse(String inputDateAndTime) {
        return new DateAndTime(inputDateAndTime);
    }

}
