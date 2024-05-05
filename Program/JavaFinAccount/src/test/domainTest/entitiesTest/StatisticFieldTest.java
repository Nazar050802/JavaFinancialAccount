package domainTest.entitiesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticFieldTest {

    private StatisticField statisticField;

    @BeforeEach
    void setUp() throws InvalidStatisticField {
        statisticField = new StatisticField();
    }

    @Test
    @DisplayName("Successfully create a StatisticField with initial values")
    void testCreateStatisticFieldSuccessfully() throws InvalidStatisticField {
        StatisticField newField = new StatisticField(100.0, 50.0);
        assertAll("Verify all properties are set correctly",
                () -> assertEquals(100.0, newField.getIncome()),
                () -> assertEquals(50.0, newField.getExpense()),
                () -> assertEquals(50.0, newField.getProfitability())
        );
    }

    @Test
    @DisplayName("Fail to create StatisticField with negative income")
    void testCreateWithNegativeIncome() {
        InvalidStatisticField thrown = assertThrows(InvalidStatisticField.class, () ->
                new StatisticField(-10.0, 50.0));
        assertTrue(thrown.getMessage().contains("Income cannot be negative"));
    }

    @Test
    @DisplayName("Fail to create StatisticField with negative expense")
    void testCreateWithNegativeExpense() {
        InvalidStatisticField thrown = assertThrows(InvalidStatisticField.class, () ->
                new StatisticField(10.0, -50.0));
        assertTrue(thrown.getMessage().contains("Expense cannot be negative"));
    }

    @Test
    @DisplayName("Successfully update income and automatically update profitability")
    void testUpdateIncome() throws InvalidStatisticField {
        statisticField.setIncome(200.0);
        statisticField.setExpense(100.0);
        assertEquals(100.0, statisticField.getProfitability(), "Profitability should be income minus expense");
    }

    @Test
    @DisplayName("Successfully update expense and automatically update profitability")
    void testUpdateExpense() throws InvalidStatisticField {
        statisticField.setIncome(300.0);
        statisticField.setExpense(150.0);
        assertEquals(150.0, statisticField.getProfitability(), "Profitability should be income minus expense");
    }

    @Test
    @DisplayName("Attempt to set negative income")
    void testSetNegativeIncome() {
        InvalidStatisticField thrown = assertThrows(InvalidStatisticField.class, () ->
                statisticField.setIncome(-1.0));
        assertTrue(thrown.getMessage().contains("Income cannot be negative"));
    }

    @Test
    @DisplayName("Attempt to set negative expense")
    void testSetNegativeExpense() {
        InvalidStatisticField thrown = assertThrows(InvalidStatisticField.class, () ->
                statisticField.setExpense(-1.0));
        assertTrue(thrown.getMessage().contains("Expense cannot be negative"));
    }
}
