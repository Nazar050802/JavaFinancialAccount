package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.application.utils.DateUtilities;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller for handling record display and manipulation.
 */
public class ShowRecordsController {
    private final ControllerCore controllerCore;
    public static final int PAGE_SIZE = 10;

    /**
     * Constructs a ShowRecordsController with a reference to the central controller.
     * @param controllerCore The core controller containing service configurations.
     */
    public ShowRecordsController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Retrieves a paginated list of records, sorted by date.
     * @param ascending Specifies the sorting order; true for ascending, false for descending.
     * @param page The page number to retrieve.
     * @return A list of records for the specified page.
     */
    public List<Record> getSortedRecords(boolean ascending, int page) {
        return getPaginatedRecords(getSortedRecords(ascending), page);
    }

    /**
     * Retrieves a paginated list of records for a specific date.
     *
     * @param date The date for which records are to be fetched.
     * @param page The page number to retrieve.
     * @return A list of records that match the given date on the specified page.
     */
    public List<Record> getRecordsByDate(DateAndTime date, int page) {
        return getPaginatedRecords(getRecordsByDate_Format_dd_mm_yyyy(date), page);
    }

    /**
     * Retrieves a paginated list of records for a specific category.
     *
     * @param category The category for which records are to be fetched.
     * @param page The page number to retrieve.
     * @return A list of records that belong to the given category on the specified page.
     */
    public List<Record> getRecordsByCategory(String category, int page) {
        return getPaginatedRecords(getRecordsByCategory(category), page);
    }

    /**
     * Applies pagination to a list of records based on the specified page number.
     *
     * @param records The list of records to paginate.
     * @param page The page number to retrieve.
     * @return A sublist of records that fall within the page limits.
     */
    private List<Record> getPaginatedRecords(List<Record> records, int page) {
        int start = (page - 1) * PAGE_SIZE;

        if (start >= records.size()) {
            return Collections.emptyList();
        }
        int end = Math.min(start + PAGE_SIZE, records.size());
        return records.subList(start, end);
    }

    /**
     * Sorts records either in ascending or descending order based on their date and time.
     *
     * @param ascending A boolean indicating if the sorting should be ascending (true) or descending (false).
     * @return A list of records sorted by date and time.
     */
    private List<Record> getSortedRecords(boolean ascending) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .sorted((r1, r2) -> ascending ?
                        DateUtilities.compare(r1.getDateAndTime(), r2.getDateAndTime()) :
                        DateUtilities.compare(r2.getDateAndTime(), r1.getDateAndTime()))
                .collect(Collectors.toList());
    }

    /**
     * Filters records to find those that match a specific date.
     *
     * @param date The date to match records against, in the format dd mm yyyy.
     * @return A list of records that have the specified date.
     */
    private List<Record> getRecordsByDate_Format_dd_mm_yyyy(DateAndTime date) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .filter(record -> DateUtilities.compareFormat_Format_dd_mm_yyyy(record.getDateAndTime(), date) == 0)
                .collect(Collectors.toList());
    }

    /**
     * Filters records to find those that belong to a specific category.
     *
     * @param category The category name to match records against.
     * @return A list of records that belong to the specified category.
     */
    private List<Record> getRecordsByCategory(String category) {
        return StreamSupport.stream(controllerCore.serviceContainer.getRecordService().getAllRecords().spliterator(), false)
                .filter(record -> record.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a record by its identifier.
     * @param id The unique identifier of the record to delete.
     * @return true if the record was found and deleted, false otherwise.
     */
    public boolean deleteRecordById(Long id) {
        Record recordToDelete = controllerCore.serviceContainer.getRecordService().getRecord(id).orElse(null);

        if (recordToDelete != null) {
            controllerCore.serviceContainer.getRecordService().deleteRecord(recordToDelete);

            controllerCore.tryToSaveDataToFile();

            return true;
        }

        return false;
    }
}
