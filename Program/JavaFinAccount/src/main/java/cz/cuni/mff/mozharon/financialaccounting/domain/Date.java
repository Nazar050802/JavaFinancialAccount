package cz.cuni.mff.mozharon.financialaccounting.domain;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Date {
    private static final Logger logger = Logger.getLogger(Date.class.getName());

    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public boolean setYear(int year) {
        if(year > 0){
            this.year = year;
            return true;
        }
        else
        {
            logger.log(Level.WARNING, "The year ");
            return false;
        }

    }

    public int getMonth() {
        return month;
    }

    public boolean setMonth(int month) {
        if(month >= 1 && month <= 12){
            this.month = month;
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getDay() {
        return day;
    }

    public boolean setDay(int day) {
        if(day >= 1 && day <= 31){
            this.day = day;
            return true;
        }
        else
        {
            return false;
        }
    }
}
