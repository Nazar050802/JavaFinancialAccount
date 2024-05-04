package cz.cuni.mff.mozharon.financialaccounting.application.utils;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;

import java.time.LocalDateTime;

public class DateUtilities {
    public static int compare(DateAndTime dt1, DateAndTime dt2) {
        LocalDateTime ldt1 = LocalDateTime.of(dt1.getYear(), dt1.getMonth(), dt1.getDay(), dt1.getHours(), dt1.getMinutes(), dt1.getSeconds());
        LocalDateTime ldt2 = LocalDateTime.of(dt2.getYear(), dt2.getMonth(), dt2.getDay(), dt2.getHours(), dt2.getMinutes(), dt2.getSeconds());
        return ldt1.compareTo(ldt2);
    }

    public static int compareFormat_Format_dd_mm_yyyy(DateAndTime dt1, DateAndTime dt2) {
        LocalDateTime ldt1 = LocalDateTime.of(dt1.getYear(), dt1.getMonth(), dt1.getDay(), 0, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(dt2.getYear(), dt2.getMonth(), dt2.getDay(), 0, 0, 0);
        return ldt1.compareTo(ldt2);
    }
}
