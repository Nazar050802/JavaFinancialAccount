package applicationTest.servicesTest;

import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.services.StatisticCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for StatisticsService.
 */
class StatisticsServiceTest {
    private StatisticCalculatorService calculatorService;
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() throws Exception {
        calculatorService = mock(StatisticCalculatorService.class);
        statisticsService = new StatisticsService();

        Field calculatorServiceField = StatisticsService.class.getDeclaredField("calculatorService");
        calculatorServiceField.setAccessible(true);
        calculatorServiceField.set(statisticsService, calculatorService);
    }

    @Test
    @DisplayName("getYearlyStatistics should return valid statistics")
    void testGetYearlyStatistics() throws InvalidStatisticField {
        List<Record> records = List.of(mock(Record.class));
        Map<Integer, StatisticField> expected = new HashMap<>();
        expected.put(2020, new StatisticField());

        when(calculatorService.calculateYearStatistic(records)).thenReturn((HashMap<Integer, StatisticField>) expected);

        Map<Integer, StatisticField> result = statisticsService.getYearlyStatistics(records);
        assertEquals(expected, result);
        verify(calculatorService).calculateYearStatistic(records);
    }

    @Test
    @DisplayName("getMonthlyStatistics should handle InvalidStatisticField exception")
    void testGetMonthlyStatisticsException() throws InvalidStatisticField {
        List<Record> records = List.of(mock(Record.class));
        when(calculatorService.calculateMonthStatistic(records)).thenThrow(new InvalidStatisticField("Invalid data"));

        assertThrows(InvalidStatisticField.class, () -> {
            statisticsService.getMonthlyStatistics(records);
        });
    }

    @Test
    @DisplayName("getDailyStatistics should return detailed daily statistics")
    void testGetDailyStatistics() throws InvalidStatisticField {
        List<Record> records = List.of(mock(Record.class));
        Map<Integer, Map<Integer, Map<Integer, StatisticField>>> expected = new HashMap<>();

        when(calculatorService.calculateDayStatistic(records)).thenReturn(expected);

        Map<Integer, Map<Integer, Map<Integer, StatisticField>>> result = statisticsService.getDailyStatistics(records);
        assertNotNull(result);
        verify(calculatorService).calculateDayStatistic(records);
    }
}
