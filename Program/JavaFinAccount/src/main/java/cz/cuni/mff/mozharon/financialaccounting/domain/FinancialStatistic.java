package cz.cuni.mff.mozharon.financialaccounting.domain;

import cz.cuni.mff.mozharon.financialaccounting.config.LoggerConfig;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FinancialStatistic {
    private static final Logger logger = LoggerConfig.getLogger(FinancialStatistic.class);

    private List<Record> records;

    private HashMap<Integer, StatisticField> yearStatistic;

    private HashMap<Integer, StatisticField> monthsStatistic;

    private HashMap<Integer, StatisticField> currentMonthStatistic;

    private HashMap<Integer, StatisticField> currentDayStatistic;

    public HashMap<Integer, StatisticField> getYearStatistic() throws InvalidStatisticField {
        extractYearStatistic();
        return yearStatistic;
    }

    private void extractYearStatistic() throws InvalidStatisticField {

        HashMap<Integer, StatisticField> allYears = new HashMap<>();
        for (Record record: records) {
            int year = record.getDateAndTime().getYear();

            // If the year doesn't exist, add it to the list
            if (!allYears.containsKey(year)) {
                allYears.put(year, new StatisticField());
            }
        }

        // Calculate all expenses and more
        for (Record record: records) {
            int year = record.getDateAndTime().getYear();

            StatisticField currentStatistic = allYears.get(year);

            if(record.getRecordType() == Record.RecordType.INCOME){
                BigDecimal tempIncome = currentStatistic.getIncome();
                tempIncome = tempIncome.add(record.getAmount());
                currentStatistic.setIncome(tempIncome);
            } else if (record.getRecordType() == Record.RecordType.EXPENSE) {
                BigDecimal tempExpense = currentStatistic.getExpense();
                tempExpense = tempExpense.add(record.getAmount());
                currentStatistic.setExpense(tempExpense);
            }

            allYears.put(year, currentStatistic);
        }

        this.yearStatistic = allYears;
    }

    public FinancialStatistic(List<Record> records){
        this.records = records;
    }
}
