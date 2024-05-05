package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;

import java.util.Optional;

/**
 * Provides services for managing records in the financial accounting system.
 */
public class RecordService {
    private final RecordRepositoryInterface recordRepository;

    /**
     * Constructs a RecordService with a dependency on a record repository.
     *
     * @param recordRepository The repository to be used for data access operations.
     */
    public RecordService(RecordRepositoryInterface recordRepository) {
        this.recordRepository = recordRepository;
    }

    /**
     * Adds a new record to the system.
     *
     * @param record The record to add.
     * @return The added record.
     */
    public Record addRecord(Record record) {
        return recordRepository.addRecord(record);
    }

    /**
     * Deletes a record from the system.
     *
     * @param record The record to delete.
     */
    public void deleteRecord(Record record) {
        recordRepository.deleteRecord(record);
    }

    /**
     * Retrieves a record by its ID.
     *
     * @param id The ID of the record to retrieve.
     * @return An Optional containing the record if found, or an empty Optional if no record is found.
     */
    public Optional<Record> getRecord(Long id) {
        return recordRepository.findById(id);
    }

    /**
     * Retrieves all records stored in the system.
     *
     * @return An Iterable containing all the records.
     */
    public Iterable<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    /**
     * Creates a new record with specified details and adds it to the repository.
     *
     * @param amount The amount of the record.
     * @param description The description of the record.
     * @param dateAndTime The date and time of the record.
     * @param category The category associated with the record.
     * @param recordType The type of the record (income or expense).
     * @return The newly created record.
     * @throws InvalidAmountException If the amount is invalid (e.g., negative).
     */
    public Record createRecord(Double amount, String description, String dateAndTime, Category category, String recordType) throws InvalidAmountException {
        return addRecord(recordRepository.createRecord(recordRepository.getLastId() + 1, amount, description, new DateAndTime(dateAndTime), category, RecordType.valueOf(recordType.toUpperCase())));
    }
}
