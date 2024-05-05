package cz.cuni.mff.mozharon.financialaccounting.domain.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides methods to calculate various statistical metrics from financial records.
 * This service is responsible for aggregating data into yearly, monthly, and daily statistics.
 */
public class StatisticCalculatorService {

    /**
     * Calculates and returns yearly statistics based on the provided list of records.
     *
     * @param records List of records to calculate statistics from.
     * @return A HashMap where keys are years and values are corresponding StatisticFields.
     * @throws InvalidStatisticField If an invalid statistic field is encountered during calculation.
     */
    public HashMap<Integer, StatisticField> calculateYearStatistic(List<Record> records) throws InvalidStatisticField {
        HashMap<Integer, StatisticField> yearStatistics = new HashMap<>();

        for (Record record : records) {
            int year = record.getDateAndTime().getYear();
            StatisticField yearStatistic = yearStatistics.computeIfAbsent(year, k -> {
                try {
                    return new StatisticField();
                } catch (InvalidStatisticField e) {
                    throw new RuntimeException(e);
                }
            });

            updateStatisticField(yearStatistic, record);
        }

        return yearStatistics;
    }

    /**
     * Calculates and returns monthly statistics based on the provided list of records.
     *
     * @param records List of records to calculate statistics from.
     * @return A nested Map where the outer key is the year, inner key is the month, and value is the StatisticField.
     * @throws InvalidStatisticField If an invalid statistic field is encountered during calculation.
     */
    public Map<Integer, Map<Integer, StatisticField>> calculateMonthStatistic(List<Record> records) throws InvalidStatisticField {
        Map<Integer, Map<Integer, StatisticField>> monthStatistics = new HashMap<>();

        for (Record record : records) {
            int year = record.getDateAndTime().getYear();
            int month = record.getDateAndTime().getMonth();

            monthStatistics.computeIfAbsent(year, k -> new HashMap<>())
                    .computeIfAbsent(month, k -> {
                        try {
                            return new StatisticField();
                        } catch (InvalidStatisticField e) {
                            throw new RuntimeException(e);
                        }
                    });

            updateStatisticField(monthStatistics.get(year).get(month), record);
        }

        return monthStatistics;
    }

    
    /**
     * Calculates and returns daily statistics based on the provided list of records.
     *
     * @param records List of records to calculate statistics from.
     * @return A deeply nested Map where the outer key is the year, middle key is the month, inner key is the day, and value is the StatisticField.
     * @throws InvalidStatisticField If an invalid statistic field is encountered during calculation.
     */
    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>> calculateDayStatistic(List<Record> records) throws InvalidStatisticField {
        Map<Integer, Map<Integer, Map<Integer, StatisticField>>> dayStatistics = new HashMap<>();

        for (Record record : records) {
            int year = record.getDateAndTime().getYear();
            int month = record.getDateAndTime().getMonth();
            int day = record.getDateAndTime().getDay();

            dayStatistics.computeIfAbsent(year, k -> new HashMap<>())
                    .computeIfAbsent(month, k -> new HashMap<>())
                    .computeIfAbsent(day, k -> {
                        try {
                            return new StatisticField();
                        } catch (InvalidStatisticField e) {
                            throw new RuntimeException(e);
                        }
                    });

            updateStatisticField(dayStatistics.get(year).get(month).get(day), record);
        }

        return dayStatistics;
    }

    /**
     * Updates the provided StatisticField based on the record's type and amount.
     *
     * @param statisticField The StatisticField to update.
     * @param record The record from which to extract the data.
     * @throws InvalidStatisticField If an invalid value is encountered during the update.
     */
    private void updateStatisticField(StatisticField statisticField, Record record) throws InvalidStatisticField {
        Double amount = record.getAmount();
        if (record.getRecordType() == RecordType.INCOME) {
            statisticField.setIncome(statisticField.getIncome() + amount);
        } else if (record.getRecordType() == RecordType.EXPENSE) {
            statisticField.setExpense(statisticField.getExpense() + amount);
        }
    }
}
