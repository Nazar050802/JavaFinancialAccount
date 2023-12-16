package cz.cuni.mff.mozharon;


import cz.cuni.mff.mozharon.financialaccounting.domain.entities.*;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
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
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime(""), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime(""), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("10 2 2022 10 10 23"), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("10 2 2022 10 10 23"), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("11 5 2022 10 10 23"), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 5 2022 10 10 23"), category1, RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("13 2 2022 10 10 23"), category1, RecordType.INCOME));
        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", new DateAndTime(""), category1, RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", new DateAndTime(""), category1, RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("11 3 2021 10 10 23"), category1, RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 3 2021 10 10 23"), category1, RecordType.EXPENSE));
        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 3 2021 10 10 23"), category1, RecordType.EXPENSE));

        FinancialStatistic financialStatistic = new FinancialStatistic(recordList);
        financialStatistic.getYearsStatistic();

    }
}

