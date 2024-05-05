package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.services.StatisticCalculatorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    private StatisticCalculatorService calculatorService;

    public StatisticsService() {
        this.calculatorService = new StatisticCalculatorService();
    }

    public Map<Integer, StatisticField> getYearlyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateYearStatistic(records);
    }

    public Map<Integer, Map<Integer, StatisticField>> getMonthlyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateMonthStatistic(records);
    }

    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>> getDailyStatistics(List<Record> records) throws InvalidStatisticField {
        return calculatorService.calculateDayStatistic(records);
    }
}
