package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidDateException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidTimeException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents date and time details within the financial accounting application.
 * This class encapsulates date and time information with methods for validation.
 */
public class DateAndTime {

    private int year;
    private int month;
    private int day;

    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Sets the year.
     *
     * @param year The year to set.
     * @throws InvalidDateException If the year is not positive.
     */
    public void setYear(int year) throws InvalidDateException {
        if (year <= 0) {
            throw new InvalidDateException("The year has an invalid value. It must be greater than 0.");
        }
        this.year = year;
    }

    /**
     * Sets the month.
     *
     * @param month The month to set (1-12).
     * @throws InvalidDateException If the month is not within the valid range.
     */
    public void setMonth(int month) throws InvalidDateException {
        if (month < 1 || month > 12) {
            throw new InvalidDateException("The month has an invalid value. It must be between 1 and 12.");
        }
        this.month = month;
    }

    /**
     * Sets the day.
     *
     * @param day The day to set (1-31).
     * @throws InvalidDateException If the day is not within the valid range.
     */
    public void setDay(int day) throws InvalidDateException {
        if (day < 1 || day > 31) {
            throw new InvalidDateException("The day has an invalid value. It must be between 1 and 31.");
        }
        this.day = day;
    }

    /**
     * Sets the hours.
     *
     * @param hours The hours to set (0-23).
     * @throws InvalidTimeException If the hours are not within the valid range.
     */
    public void setHours(int hours) throws InvalidTimeException {
        if (hours < 0 || hours > 23) {
            throw new InvalidTimeException("The hours have an invalid value. It must be between 0 and 23.");
        }
        this.hours = hours;
    }

    /**
     * Sets the minutes.
     *
     * @param minutes The minutes to set (0-59).
     * @throws InvalidTimeException If the minutes are not within the valid range.
     */
    public void setMinutes(int minutes) throws InvalidTimeException {
        if (minutes < 0 || minutes > 59) {
            throw new InvalidTimeException("The minutes have an invalid value. It must be between 0 and 59.");
        }
        this.minutes = minutes;
    }

    /**
     * Sets the seconds.
     *
     * @param seconds The seconds to set (0-59).
     * @throws InvalidTimeException If the seconds are not within the valid range.
     */
    public void setSeconds(int seconds) throws InvalidTimeException {
        if (seconds < 0 || seconds > 59) {
            throw new InvalidTimeException("The seconds have an invalid value. It must be between 0 and 59.");
        }
        this.seconds = seconds;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * Constructs a DateAndTime object from a formatted string.
     *
     * @param dateAndTime The date and time string in "dd mm yyyy hh mm ss" format.
     */
    public DateAndTime(String dateAndTime){
        setDate(dateAndTime);
    }

    /**
     * Constructs a DateAndTime object from individual date and time components.
     *
     * @param day   The day of the month (1-31).
     * @param month The month of the year (1-12).
     * @param year  The year.
     * @param hours The hour of the day (0-23).
     * @param minutes The minute of the hour (0-59).
     * @param seconds The second of the minute (0-59).
     */
    public DateAndTime(int day, int month, int year, int seconds, int minutes, int hours){
        setDate(day, month, year, seconds, hours, minutes);
    }

    /**
     * Default constructor for creating a DateAndTime object.
     */
    public DateAndTime(){}

    /**
     * <p>Sets the date and time using individual parameters, handling potential exceptions.</p>
     *
     * @param inputDay      The day of the month.
     * @param inputMonth    The month (1-12).
     * @param inputYear     The year.
     * @param inputHours    The hour of the day (0-23).
     * @param inputMinutes  The minute of the hour (0-59).
     * @param inputSeconds  The second of the minute (0-59).
     *
     * <p>Logs a warning if an exception occurs during the process, indicating potential value issues.</p>
     */
    public void setDate(int inputDay, int inputMonth, int inputYear, int inputSeconds, int inputMinutes, int inputHours){
        try {
            setDay(inputDay);
            setMonth(inputMonth);
            setYear(inputYear);
            setHours(inputHours);
            setMinutes(inputMinutes);
            setSeconds(inputSeconds);
        }
        catch (InvalidDateException | InvalidTimeException e){
            System.out.println("Something go wrong during setting Date. Check does day, month, year, " +
                    "hours, month and seconds have appropriate correct values.");
        }
    }

    /**
     * <p>
     * Sets the date and time based on a string representation.
     * Expects a string with six <b>space-separated parameters:</b> <b>day, month, year, second, minute, hour</b>.
     * If the string is empty, uses current date and time as default values.
     * </p>
     * @param inputDate A string representation of the date. Example: "25 12 2015 12 32 00""
     * @throws NumberFormatException If the input has an incorrect number of parameters.
     */
    public void setDate(String inputDate){

        int numberOfParameters = 6;

        String[] arrOfDateString = inputDate.split(" ");
        if(arrOfDateString.length != numberOfParameters && !Objects.equals(inputDate, "")){
            throw new NumberFormatException("Something go wrong during setting Date using String. Check number of parameters.");
        }

        int[] arrOfDateInt = new int[arrOfDateString.length];
        if(Objects.equals(inputDate, "")){
            arrOfDateInt = new int[numberOfParameters];

            arrOfDateInt[0] = LocalDateTime.now().getDayOfMonth();
            arrOfDateInt[1] = LocalDateTime.now().getMonthValue();
            arrOfDateInt[2] = LocalDateTime.now().getYear();

            arrOfDateInt[3] = LocalDateTime.now().getSecond();
            arrOfDateInt[4] = LocalDateTime.now().getMinute();
            arrOfDateInt[5] = LocalDateTime.now().getHour();
        }
        else {
            for (int i = 0; i < arrOfDateString.length; i++) {
                arrOfDateInt[i] = Integer.parseInt(arrOfDateString[i]);
            }
        }

        setDate(arrOfDateInt[0],  arrOfDateInt[1], arrOfDateInt[2], arrOfDateInt[3], arrOfDateInt[4], arrOfDateInt[5]);

    }
}
