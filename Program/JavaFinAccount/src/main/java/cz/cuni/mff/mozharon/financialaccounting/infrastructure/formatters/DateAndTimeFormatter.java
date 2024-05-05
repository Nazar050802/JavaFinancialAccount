package cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;

/**
 * Provides utilities for formatting and parsing DateAndTime objects to and from string representations.
 */
public class DateAndTimeFormatter {

    /**
     * Formats a DateAndTime object into a standardized external string representation.
     *
     * @param dateAndTime The DateAndTime object to format.
     * @return A string representing the formatted date and time.
     */
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

    /**
     * Parses a string representation of a date and time into a DateAndTime object.
     * The string format expected is "dd mm yyyy ss mm hh".
     *
     * @param inputDateAndTime The string to parse.
     * @return The DateAndTime object representing the parsed date and time.
     */
    public static DateAndTime parseFormatForExternalUse(String inputDateAndTime) {
        return new DateAndTime(inputDateAndTime);
    }

}
