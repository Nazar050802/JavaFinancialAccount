package domainTest.servicesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.services.StatisticCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisticCalculatorServiceTest {

    private StatisticCalculatorService service;

    @Mock
    private Record record;

    @Mock
    private DateAndTime dateAndTime;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new StatisticCalculatorService();

        // Set up the mock DateAndTime to return specific values for year, month, and day
        when(dateAndTime.getYear()).thenReturn(2020);
        when(dateAndTime.getMonth()).thenReturn(5);
        when(dateAndTime.getDay()).thenReturn(15);

        // Make sure that when record.getDateAndTime() is called, the mocked DateAndTime object is returned
        when(record.getDateAndTime()).thenReturn(dateAndTime);
    }

    @Test
    @DisplayName("Calculate yearly statistics with valid records")
    void calculateYearlyStatistics() throws InvalidStatisticField {
        // Prepare data
        List<Record> records = new ArrayList<>();
        when(record.getAmount()).thenReturn(100.0);
        when(record.getRecordType()).thenReturn(RecordType.INCOME);
        records.add(record);

        // Act
        var result = service.calculateYearStatistic(records);

        // Assert
        assertFalse(result.isEmpty());
        assertTrue(result.containsKey(2020));
        assertEquals(100.0, result.get(2020).getIncome());
    }

    @Test
    @DisplayName("Calculate monthly statistics and verify correct aggregation")
    void calculateMonthlyStatistics() throws InvalidStatisticField {
        // Prepare data
        List<Record> records = new ArrayList<>();
        when(record.getAmount()).thenReturn(200.0);
        when(record.getRecordType()).thenReturn(RecordType.EXPENSE);
        records.add(record);

        // Act
        var result = service.calculateMonthStatistic(records);

        // Assert
        assertFalse(result.isEmpty());
        assertTrue(result.get(2020).containsKey(5));
        assertEquals(200.0, result.get(2020).get(5).getExpense());
    }

    @Test
    @DisplayName("Calculate daily statistics for mixed record types")
    void calculateDayStatistics() throws InvalidStatisticField {
        // Prepare data
        List<Record> records = new ArrayList<>();
        when(record.getAmount()).thenReturn(300.0);
        when(record.getRecordType()).thenReturn(RecordType.INCOME);
        records.add(record);

        // Act
        var result = service.calculateDayStatistic(records);

        // Assert
        assertFalse(result.isEmpty());
        assertTrue(result.get(2020).get(5).containsKey(15));
        assertEquals(300.0, result.get(2020).get(5).get(15).getIncome());
    }
}