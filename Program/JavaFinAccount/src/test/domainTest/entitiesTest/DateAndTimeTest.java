package domainTest.entitiesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidDateException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateAndTimeTest {

    private DateAndTime dateAndTime;

    @BeforeEach
    void setUp() {
        dateAndTime = new DateAndTime();
    }

    @Test
    @DisplayName("Successfully set and get year")
    void testSetAndGetYear() throws InvalidDateException {
        dateAndTime.setYear(2020);
        assertEquals(2020, dateAndTime.getYear());
    }

    @Test
    @DisplayName("Fail to set year with invalid value")
    void testSetYearInvalid() {
        assertThrows(InvalidDateException.class, () -> dateAndTime.setYear(-1));
    }

    @Test
    @DisplayName("Successfully set and get month")
    void testSetAndGetMonth() throws InvalidDateException {
        dateAndTime.setMonth(12);
        assertEquals(12, dateAndTime.getMonth());
    }

    @Test
    @DisplayName("Fail to set month with invalid value")
    void testSetMonthInvalid() {
        assertThrows(InvalidDateException.class, () -> dateAndTime.setMonth(13));
    }

    @Test
    @DisplayName("Successfully set and get day")
    void testSetAndGetDay() throws InvalidDateException {
        dateAndTime.setDay(15);
        assertEquals(15, dateAndTime.getDay());
    }

    @Test
    @DisplayName("Fail to set day with invalid value")
    void testSetDayInvalid() {
        assertThrows(InvalidDateException.class, () -> dateAndTime.setDay(32));
    }

    @Test
    @DisplayName("Successfully set and get hours")
    void testSetAndGetHours() throws InvalidTimeException {
        dateAndTime.setHours(23);
        assertEquals(23, dateAndTime.getHours());
    }

    @Test
    @DisplayName("Fail to set hours with invalid value")
    void testSetHoursInvalid() {
        assertThrows(InvalidTimeException.class, () -> dateAndTime.setHours(24));
    }

    @Test
    @DisplayName("Successfully set and get minutes")
    void testSetAndGetMinutes() throws InvalidTimeException {
        dateAndTime.setMinutes(59);
        assertEquals(59, dateAndTime.getMinutes());
    }

    @Test
    @DisplayName("Fail to set minutes with invalid value")
    void testSetMinutesInvalid() {
        assertThrows(InvalidTimeException.class, () -> dateAndTime.setMinutes(60));
    }

    @Test
    @DisplayName("Successfully set and get seconds")
    void testSetAndGetSeconds() throws InvalidTimeException {
        dateAndTime.setSeconds(59);
        assertEquals(59, dateAndTime.getSeconds());
    }

    @Test
    @DisplayName("Fail to set seconds with invalid value")
    void testSetSecondsInvalid() {
        assertThrows(InvalidTimeException.class, () -> dateAndTime.setSeconds(60));
    }

    @Test
    @DisplayName("Successfully set date with constructor")
    void testConstructorWithDate() throws InvalidDateException, InvalidTimeException {
        DateAndTime newDateAndTime = new DateAndTime(1, 1, 2020, 0, 0, 0);
        assertAll(
                () -> assertEquals(1, newDateAndTime.getDay()),
                () -> assertEquals(1, newDateAndTime.getMonth()),
                () -> assertEquals(2020, newDateAndTime.getYear()),
                () -> assertEquals(0, newDateAndTime.getHours()),
                () -> assertEquals(0, newDateAndTime.getMinutes()),
                () -> assertEquals(0, newDateAndTime.getSeconds())
        );
    }

    @Test
    @DisplayName("Successfully parse date from string")
    void testSetDateFromString() {
        dateAndTime.setDate("25 1 2020 0 32 12");
        assertAll(
                () -> assertEquals(25, dateAndTime.getDay()),
                () -> assertEquals(1, dateAndTime.getMonth()),
                () -> assertEquals(2020, dateAndTime.getYear()),
                () -> assertEquals(0, dateAndTime.getSeconds()),
                () -> assertEquals(32, dateAndTime.getMinutes()),
                () -> assertEquals(12, dateAndTime.getHours())
        );
    }

    @Test
    @DisplayName("Fail to parse date from invalid string")
    void testSetDateFromStringInvalid() {
        assertThrows(NumberFormatException.class, () -> dateAndTime.setDate("25 12"));
    }
}
