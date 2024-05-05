package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowStatisticsController {

    private ControllerCore controllerCore;

    public ShowStatisticsController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    public Map<Integer, StatisticField> getYearlyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getYearlyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    public Map<Integer, Map<Integer, StatisticField>> getMonthlyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getMonthlyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>>  getDailyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getDailyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    private List<Record> convertIterableRecordsIntoList(Iterable<Record> iterableRecords) {
        List<Record> recordsList = new ArrayList<>();

        for (Record record : iterableRecords) {
            recordsList.add(record);
        }

        return recordsList;
    }

}
