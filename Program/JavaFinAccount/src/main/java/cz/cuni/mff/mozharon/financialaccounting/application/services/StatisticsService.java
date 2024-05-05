package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.services.StatisticCalculatorService;

import java.util.List;
import java.util.Map;

/**
 * Service class that provides functionalities to calculate and retrieve various statistical data.
 */
public class StatisticsService {
    private StatisticCalculatorService calculatorService;

    /**
     * Constructs a new StatisticsService which will use a StatisticCalculatorService.
     */
    public StatisticsService() {
        this.calculatorService = new StatisticCalculatorService();
    }

    /**
     * Retrieves yearly statistical data for given records.
     *
     * @param records A list of records to calculate statistics from.
     * @return A map containing year keys and their corresponding statistical data.
     * @throws InvalidStatisticField If an invalid field is encountered during statistics calculation.
     */
    public Map<Integer, StatisticField> getYearlyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateYearStatistic(records);
    }

    /**
     * Retrieves monthly statistical data for given records.
     *
     * @param records A list of records to calculate statistics from.
     * @return A nested map containing year keys mapped to months and their corresponding statistical data.
     * @throws InvalidStatisticField If an invalid field is encountered during statistics calculation.
     */
    public Map<Integer, Map<Integer, StatisticField>> getMonthlyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateMonthStatistic(records);
    }

    /**
     * Retrieves daily statistical data for given records.
     *
     * @param records A list of records to calculate statistics from.
     * @return A deeply nested map containing year keys mapped to months and days with their corresponding statistical data.
     * @throws InvalidStatisticField If an invalid field is encountered during statistics calculation.
     */
    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>> getDailyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateDayStatistic(records);
    }
}
