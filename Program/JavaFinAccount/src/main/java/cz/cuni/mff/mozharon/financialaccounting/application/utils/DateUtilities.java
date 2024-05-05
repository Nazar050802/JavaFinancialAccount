package cz.cuni.mff.mozharon.financialaccounting.application.utils;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;

import java.time.LocalDateTime;

/**
 * Utility class for comparing and manipulating dates and times.
 */
public class DateUtilities {

    /**
     * Compares two DateAndTime objects down to the second.
     *
     * @param dt1 the first date and time.
     * @param dt2 the second date and time.
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public static int compare(DateAndTime dt1, DateAndTime dt2) {
        LocalDateTime ldt1 = LocalDateTime.of(dt1.getYear(), dt1.getMonth(), dt1.getDay(), dt1.getHours(), dt1.getMinutes(), dt1.getSeconds());
        LocalDateTime ldt2 = LocalDateTime.of(dt2.getYear(), dt2.getMonth(), dt2.getDay(), dt2.getHours(), dt2.getMinutes(), dt2.getSeconds());
        return ldt1.compareTo(ldt2);
    }

    /**
     * Compares two DateAndTime objects considering only the date part (ignoring time).
     *
     * @param dt1 the first date.
     * @param dt2 the second date.
     * @return a negative integer, zero, or a positive integer as the first date is earlier, the same, or later than the second date.
     */
    public static int compareFormat_Format_dd_mm_yyyy(DateAndTime dt1, DateAndTime dt2) {
        LocalDateTime ldt1 = LocalDateTime.of(dt1.getYear(), dt1.getMonth(), dt1.getDay(), 0, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(dt2.getYear(), dt2.getMonth(), dt2.getDay(), 0, 0, 0);
        return ldt1.compareTo(ldt2);
    }
}
