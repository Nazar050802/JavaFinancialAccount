package cz.cuni.mff.mozharon;


import cz.cuni.mff.mozharon.financialaccounting.domain.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.FinancialStatistic;
import cz.cuni.mff.mozharon.financialaccounting.domain.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvalidAmountException, InvalidStatisticField {

        DateAndTime dateAndTime = new DateAndTime();

        dateAndTime.setDate("");

        System.out.println(dateAndTime.getYear());
        System.out.println(dateAndTime.getMonth());
        System.out.println(dateAndTime.getDay());
        System.out.println(dateAndTime.getHours());
        System.out.println(dateAndTime.getMinutes());
        System.out.println(dateAndTime.getSeconds());

        Category category1 = new Category("category1");

        List<Record> recordList = new ArrayList<>();
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "10 2 2022 10 10 23", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "10 2 2022 10 10 23", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "11 5 2022 10 10 23", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "12 5 2022 10 10 23", category1, Record.RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "13 2 2022 10 10 23", category1, Record.RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", "", category1, Record.RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", "", category1, Record.RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "11 3 2021 10 10 23", category1, Record.RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "12 3 2021 10 10 23", category1, Record.RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", "12 3 2021 10 10 23", category1, Record.RecordType.EXPENSE));

        FinancialStatistic financialStatistic = new FinancialStatistic(recordList);
        financialStatistic.getYearsStatistic();

    }
}

