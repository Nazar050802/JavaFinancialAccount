package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

import java.util.Optional;

/**
 * Interface defining the operations for managing financial records within the application.
 * This includes operations for creating, finding, adding, and deleting records.
 */
public interface RecordRepositoryInterface {

    /**
     * Creates a new financial record.
     *
     * @param id The ID to assign to the new record.
     * @param amount The financial amount of the record.
     * @param description The description of the record.
     * @param dateAndTime The date and time of the record.
     * @param category The category associated with the record.
     * @param recordType The type of the record (income or expense).
     * @return The newly created record.
     * @throws InvalidAmountException If the amount is invalid.
     */
    Record createRecord(int id, Double amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException;

    /**
     * Finds a record by its ID.
     *
     * @param id The ID of the record to find.
     * @return An Optional containing the found record, or an empty Optional if no record is found.
     */
    Optional<Record> findById(Long id);

    /**
     * Adds a record to the repository.
     *
     * @param record The record to add.
     * @return The added record.
     */
    Record addRecord(Record record);

    /**
     * Deletes a record from the repository.
     *
     * @param record The record to delete.
     */
    void deleteRecord(Record record);

    /**
     * Retrieves all records in the repository.
     *
     * @return An iterable of all records.
     */
    Iterable<Record> findAll();


    /**
     * Retrieves the last assigned ID within the repository.
     *
     * @return The last assigned ID.
     */
    int getLastId();
}
