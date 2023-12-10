package cz.cuni.mff.mozharon.financialaccounting.domain;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinancialStatistic {
    private List<Record> records;
    private HashMap<Integer, StatisticField> yearsStatistic;
    private Map<Integer, Map<Integer, StatisticField>> monthsStatistic;
    private Map<Integer, Map<Integer, Map<Integer, StatisticField>>> daysStatistic;

    public FinancialStatistic(List<Record> records) throws InvalidStatisticField {
        this.records = records;

        calculateWholeStatistics();
    }

    private void calculateWholeStatistics() throws InvalidStatisticField {
        StatisticCalculator calculator = new StatisticCalculator();

        this.yearsStatistic = calculator.calculateYearStatistic(records);
        this.monthsStatistic = calculator.calculateMonthStatistic(records);
        this.daysStatistic = calculator.calculateDayStatistic(records);
    }

    public HashMap<Integer, StatisticField> getYearsStatistic() {
        return yearsStatistic;
    }

    public Map<Integer, Map<Integer, StatisticField>> getMonthsStatistic() {
        return monthsStatistic;
    }

    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>> getDaysStatistic() {
        return daysStatistic;
    }
}
