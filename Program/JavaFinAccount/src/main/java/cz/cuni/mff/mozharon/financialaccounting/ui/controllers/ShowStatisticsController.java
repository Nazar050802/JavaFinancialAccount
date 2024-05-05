package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller for handling the display and calculation of statistical data.
 */
public class ShowStatisticsController {

    private ControllerCore controllerCore;

    /**
     * Constructs a ShowStatisticsController with a reference to the central controller.
     * @param controllerCore The core controller containing service configurations.
     */
    public ShowStatisticsController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Retrieves yearly statistics based on all records.
     * @return A map containing year keys and associated statistic values.
     * @throws InvalidStatisticField If the statistic field is invalid or cannot be calculated.
     */
    public Map<Integer, StatisticField> getYearlyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getYearlyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    /**
     * Retrieves monthly statistics for all records in the system.
     * This method maps each month to its corresponding statistical data, organized by year.
     *
     * @return A map where each key is a year and its value is another map. The nested map's keys
     * are months represented by integers, with their corresponding statistic fields as values.
     * @throws InvalidStatisticField If there's an issue calculating the statistics.
     */
    public Map<Integer, Map<Integer, StatisticField>> getMonthlyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getMonthlyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    /**
     * Retrieves daily statistics for all records, organized by year and month.
     * This method provides a detailed breakdown of statistics for every single day.
     *
     * @return A map where the first level is the year, the second level is the month, and the third level
     * is the day, each associated with their respective statistic fields.
     * @throws InvalidStatisticField If there's an issue calculating the statistics.
     */
    public Map<Integer, Map<Integer, Map<Integer, StatisticField>>>  getDailyStatistics() throws InvalidStatisticField {
        return controllerCore.serviceContainer.getStatisticsService().getDailyStatistics(
                convertIterableRecordsIntoList(controllerCore.serviceContainer.getRecordService().getAllRecords())
        );
    }

    /**
     * Converts an Iterable of records into a List.
     * This utility method is typically used to prepare data for processing in bulk operations.
     *
     * @param iterableRecords An Iterable collection of Record objects.
     * @return A List containing all records from the iterable.
     */
    private List<Record> convertIterableRecordsIntoList(Iterable<Record> iterableRecords) {
        List<Record> recordsList = new ArrayList<>();

        for (Record record : iterableRecords) {
            recordsList.add(record);
        }

        return recordsList;
    }

}
