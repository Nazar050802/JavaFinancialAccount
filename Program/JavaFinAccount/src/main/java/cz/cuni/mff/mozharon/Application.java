package cz.cuni.mff.mozharon;


import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.*;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionIncorrectCredentials;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.files.FileManagerMyOwnFileFormat;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryCategoryRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryUserRepository;
import cz.cuni.mff.mozharon.financialaccounting.ui.tui.TUICore;
import cz.cuni.mff.mozharon.financialaccounting.ui.tui.TUIHelloMessage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws InterruptedException, InvalidAmountException, InvalidStatisticField, InvalidCategoryException, InvalidUserException, NoSuchAlgorithmException, IOException, ExceptionIncorrectCredentials, ExceptionParseRecordType {

//        DateAndTime dateAndTime = new DateAndTime();
//
//        dateAndTime.setDate("");
//
//        System.out.println(dateAndTime.getYear());
//        System.out.println(dateAndTime.getMonth());
//        System.out.println(dateAndTime.getDay());
//        System.out.println(dateAndTime.getHours());
//        System.out.println(dateAndTime.getMinutes());
//        System.out.println(dateAndTime.getSeconds());
//
//        Category category1 = new Category("category1");
//
//        Category subC2 = new Category("subC21");
//        Category subC3 = new Category("subC22");
//
//        Category category2 = new Category("category2", List.of(subC2, subC3));
//        Category category3 = new Category("category3");
//
//        List<Record> recordList = new ArrayList<>();
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime(""), category1, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime(""), category1, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("10 2 2022 10 10 23"), category1, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("10 2 2022 10 10 23"), category2, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("11 5 2022 10 10 23"), category1, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 5 2022 10 10 23"), category2, RecordType.EXPENSE));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("13 2 2022 10 10 23"), category1, RecordType.INCOME));
//        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", new DateAndTime(""), category3, RecordType.EXPENSE));
//        recordList.add(new Record(BigDecimal.valueOf(3.1), "Record 1", new DateAndTime(""), category1, RecordType.EXPENSE));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("11 3 2021 10 10 23"), category1, RecordType.EXPENSE));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 3 2021 10 10 23"), category3, RecordType.EXPENSE));
//        recordList.add(new Record(BigDecimal.valueOf(2.1), "Record 1", new DateAndTime("12 3 2021 10 10 23"), category1, RecordType.EXPENSE));
//
//        FinancialStatistic financialStatistic = new FinancialStatistic(recordList);
//        financialStatistic.getYearsStatistic();
//
//        // Test save load from file
//
//        InMemoryRecordRepository inMemoryRecordRepository = new InMemoryRecordRepository();
//        RecordService recordService = new RecordService(inMemoryRecordRepository);
//
//        InMemoryCategoryRepository inMemoryCategoryRepository = new InMemoryCategoryRepository();
//        CategoryService categoryService = new CategoryService(inMemoryCategoryRepository);
//
//        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
//        UserService userService = new UserService(inMemoryUserRepository);
//
//        userService.createUser("admin", "admin");
//
//        ServiceContainer serviceContainer = new ServiceContainer(userService, recordService, categoryService);
//
////        for(Record record : recordList) {
////            recordService.addRecord(record);
////        }
////
////        for(Category category : List.of(category1, category2, category3)) {
////            categoryService.addCategory(category);
////        }
////
////
////
//        FileManagerMyOwnFileFormat fileManagerMyOwnFileFormat = new FileManagerMyOwnFileFormat();
////        fileManagerMyOwnFileFormat.saveDataToFile(serviceContainer);
//
//        ServiceContainer newServiceContainer = fileManagerMyOwnFileFormat.readDataFromFile(userService);
//
//        System.out.println("Tests");

        TUICore.main(new String[]{});

        System.out.println("");
    }
}

