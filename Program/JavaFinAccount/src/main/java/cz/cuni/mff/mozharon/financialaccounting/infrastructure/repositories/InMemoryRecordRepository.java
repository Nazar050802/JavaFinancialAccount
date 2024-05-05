package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the RecordRepositoryInterface, managing records storage.
 */
public class InMemoryRecordRepository implements RecordRepositoryInterface {

    private final ConcurrentHashMap<Long, Record> records;

    /**
     * Constructs an InMemoryRecordRepository with an empty record storage.
     */
    public InMemoryRecordRepository() {
        this.records = new ConcurrentHashMap<>();
    }

    public InMemoryRecordRepository(ConcurrentHashMap<Long, Record> records) {
        this.records = records;
    }

    /**
     * Adds a record to the in-memory storage.
     *
     * @param record the record to add
     * @return the previously associated record, or null if there was no record for the key
     */
    @Override
    public Record addRecord(Record record) {
        return records.put(record.getId(), record);
    }

    /**
     * Deletes a record from the in-memory storage.
     *
     * @param record the record to delete
     */
    @Override
    public void deleteRecord(Record record) {
        records.remove(record.getId());
    }

    /**
     * Retrieves all records from the in-memory storage.
     *
     * @return an iterable collection of records
     */
    @Override
    public Iterable<Record> findAll() {
        return records.values();
    }

    /**
     * Retrieves the last ID used for a record.
     *
     * @return the last used record ID
     */
    @Override
    public int getLastId() {
        return records.size();
    }

    /**
     * Creates a new record with provided details and stores it in the repository.
     *
     * @param id the ID for the new record
     * @param amount the amount for the new record
     * @param description the description for the new record
     * @param dateAndTime the date and time for the new record
     * @param category the category for the new record
     * @param recordType the type of the record
     * @return the newly created record
     * @throws InvalidAmountException if the amount is invalid (e.g., negative)
     */
    @Override
    public Record createRecord(int id, Double amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        return new Record(id, amount, description, dateAndTime, category, recordType);
    }

    /**
     * Finds a record by its ID.
     *
     * @param id the ID of the record to find
     * @return an Optional containing the found record, or an empty Optional if no record is found
     */
    @Override
    public Optional<Record> findById(Long id) {
        return Optional.ofNullable(records.get(id));
    }

}
