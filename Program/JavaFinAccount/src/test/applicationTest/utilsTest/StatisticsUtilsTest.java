package applicationTest.utilsTest;

import cz.cuni.mff.mozharon.financialaccounting.application.utils.StatisticsUtils;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for StatisticsUtils.
 */
class StatisticsUtilsTest {
    private Map<Integer, StatisticField> yearlyStatistics;
    private Map<Integer, Map<Integer, StatisticField>> monthlyStatistics;
    private Map<Integer, Map<Integer, Map<Integer, StatisticField>>> dailyStatistics;

    @BeforeEach
    void setUp() throws InvalidStatisticField {
        yearlyStatistics = new LinkedHashMap<>();
        StatisticField statField = new StatisticField();
        try {
            statField.setIncome(5000.0);
            statField.setExpense(3000.0);
        } catch (InvalidStatisticField e) {
            e.printStackTrace();
        }
        yearlyStatistics.put(2022, statField);

        monthlyStatistics = new LinkedHashMap<>();
        monthlyStatistics.put(2022, new LinkedHashMap<>());
        monthlyStatistics.get(2022).put(1, statField);

        dailyStatistics = new LinkedHashMap<>();
        dailyStatistics.put(2022, new LinkedHashMap<>());
        dailyStatistics.get(2022).put(1, new LinkedHashMap<>());
        dailyStatistics.get(2022).get(1).put(15, statField);
    }


    @Test
    @DisplayName("Extract yearly income statistics")
    void testExtractYearlyIncome() {
        Map<Integer, Double> expected = Map.of(2022, 5000.0);
        assertEquals(expected, StatisticsUtils.extractYearsIncome(yearlyStatistics),
                "Extracted income values should match expected values.");
    }

    @Test
    @DisplayName("Extract yearly expense statistics")
    void testExtractYearlyExpense() {
        Map<Integer, Double> expected = Map.of(2022, 3000.0);
        assertEquals(expected, StatisticsUtils.extractYearsExpense(yearlyStatistics),
                "Extracted expense values should match expected values.");
    }

    @Test
    @DisplayName("Extract yearly profit statistics")
    void testExtractYearlyProfit() {
        Map<Integer, Double> expected = Map.of(2022, 2000.0);
        assertEquals(expected, StatisticsUtils.extractYearsProfit(yearlyStatistics),
                "Extracted profit values should match expected values.");
    }

    @Test
    @DisplayName("Extract monthly income statistics for a given year")
    void testExtractMonthlyIncome() {
        Map<Integer, Double> expected = Map.of(1, 5000.0);
        assertEquals(expected, StatisticsUtils.extractMonthlyIncome(monthlyStatistics, 2022),
                "Extracted monthly income values should match expected values.");
    }

    @Test
    @DisplayName("Extract daily income statistics for a given year and month")
    void testExtractDailyIncome() {
        Map<Integer, Double> expected = Map.of(15, 5000.0);
        assertEquals(expected, StatisticsUtils.extractDailyIncome(dailyStatistics, 2022, 1),
                "Extracted daily income values should match expected values.");
    }

    @Test
    @DisplayName("Sort and convert keys to string with ascending order")
    void testSortKeysAndConvertToStringAscending() {
        Map<Integer, Double> unsorted = Map.of(2023, 1000.0, 2022, 5000.0);
        Map<String, Double> expected = new LinkedHashMap<>();
        expected.put("2022", 5000.0);
        expected.put("2023", 1000.0);
        assertEquals(expected, StatisticsUtils.sortKeysAndConvertToString(unsorted, true),
                "Sorted map should have keys in ascending order converted to strings.");
    }

    @Test
    @DisplayName("Sort and convert keys to string with descending order")
    void testSortKeysAndConvertToStringDescending() {
        Map<Integer, Double> unsorted = Map.of(2023, 1000.0, 2022, 5000.0);
        Map<String, Double> expected = new LinkedHashMap<>();
        expected.put("2023", 1000.0);
        expected.put("2022", 5000.0);
        assertEquals(expected, StatisticsUtils.sortKeysAndConvertToString(unsorted, false),
                "Sorted map should have keys in descending order converted to strings.");
    }
}
