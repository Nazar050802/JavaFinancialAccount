package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.application.utils.DateUtilities;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ShowRecordsController {
    private final ControllerCore controllerCore;
    public static final int PAGE_SIZE = 10;

    public ShowRecordsController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    public List<Record> getSortedRecords(boolean ascending, int page) {
        return getPaginatedRecords(getSortedRecords(ascending), page);
    }

    public List<Record> getRecordsByDate(DateAndTime date, int page) {
        return getPaginatedRecords(getRecordsByDate_Format_dd_mm_yyyy(date), page);
    }

    public List<Record> getRecordsByCategory(String category, int page) {
        return getPaginatedRecords(getRecordsByCategory(category), page);
    }

    private List<Record> getPaginatedRecords(List<Record> records, int page) {
        int start = (page - 1) * PAGE_SIZE;

        if (start >= records.size()) {
            return Collections.emptyList();
        }
        int end = Math.min(start + PAGE_SIZE, records.size());
        return records.subList(start, end);
    }

    private List<Record> getSortedRecords(boolean ascending) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .sorted((r1, r2) -> ascending ?
                        DateUtilities.compare(r1.getDateAndTime(), r2.getDateAndTime()) :
                        DateUtilities.compare(r2.getDateAndTime(), r1.getDateAndTime()))
                .collect(Collectors.toList());
    }

    private List<Record> getRecordsByDate_Format_dd_mm_yyyy(DateAndTime date) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .filter(record -> DateUtilities.compareFormat_Format_dd_mm_yyyy(record.getDateAndTime(), date) == 0)
                .collect(Collectors.toList());
    }

    private List<Record> getRecordsByCategory(String category) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .filter(record -> record.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
