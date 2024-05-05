package applicationTest.utilsTest;

import cz.cuni.mff.mozharon.financialaccounting.application.utils.DateUtilities;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for the DateUtilities class.
 */
class DateUtilitiesTest {

    @Test
    @DisplayName("compare should return zero if dates are exactly the same")
    void compare_IdenticalDates_ShouldReturnZero() {
        DateAndTime dt1 = mock(DateAndTime.class);
        DateAndTime dt2 = mock(DateAndTime.class);

        when(dt1.getYear()).thenReturn(2020);
        when(dt1.getMonth()).thenReturn(11);
        when(dt1.getDay()).thenReturn(17);
        when(dt1.getHours()).thenReturn(11);
        when(dt1.getMinutes()).thenReturn(30);
        when(dt1.getSeconds()).thenReturn(45);

        when(dt2.getYear()).thenReturn(2020);
        when(dt2.getMonth()).thenReturn(11);
        when(dt2.getDay()).thenReturn(17);
        when(dt2.getHours()).thenReturn(11);
        when(dt2.getMinutes()).thenReturn(30);
        when(dt2.getSeconds()).thenReturn(45);

        int result = DateUtilities.compare(dt1, dt2);
        assertEquals(0, result, "Dates are identical but did not compare as equal.");
    }

    @Test
    @DisplayName("compare should correctly order earlier and later dates")
    void compare_DifferentDates_ShouldCorrectlyOrder() {
        DateAndTime dt1 = mock(DateAndTime.class);
        DateAndTime dt2 = mock(DateAndTime.class);

        when(dt1.getYear()).thenReturn(2020);
        when(dt1.getMonth()).thenReturn(11);
        when(dt1.getDay()).thenReturn(14);
        when(dt1.getHours()).thenReturn(23);
        when(dt1.getMinutes()).thenReturn(59);
        when(dt1.getSeconds()).thenReturn(59);

        when(dt2.getYear()).thenReturn(2020);
        when(dt2.getMonth()).thenReturn(11);
        when(dt2.getDay()).thenReturn(15);
        when(dt2.getHours()).thenReturn(0);
        when(dt2.getMinutes()).thenReturn(0);
        when(dt2.getSeconds()).thenReturn(0);

        int result = DateUtilities.compare(dt1, dt2);
        assertEquals(-1, result, "dt1 should be less than dt2 but was not.");
    }

    @Test
    @DisplayName("compareFormat_Format_dd_mm_yyyy should ignore time when comparing")
    void compareFormat_Format_dd_mm_yyyy_IgnoreTimeComponent() {
        DateAndTime dt1 = mock(DateAndTime.class);
        DateAndTime dt2 = mock(DateAndTime.class);

        when(dt1.getYear()).thenReturn(2022);
        when(dt1.getMonth()).thenReturn(12);
        when(dt1.getDay()).thenReturn(15);
        when(dt1.getHours()).thenReturn(23);
        when(dt1.getMinutes()).thenReturn(59);
        when(dt1.getSeconds()).thenReturn(59);

        when(dt2.getYear()).thenReturn(2022);
        when(dt2.getMonth()).thenReturn(12);
        when(dt2.getDay()).thenReturn(15);
        when(dt2.getHours()).thenReturn(0);
        when(dt2.getMinutes()).thenReturn(0);
        when(dt2.getSeconds()).thenReturn(0);

        int result = DateUtilities.compareFormat_Format_dd_mm_yyyy(dt1, dt2);
        assertEquals(0, result, "Dates should be considered equal when ignoring time.");
    }
}
