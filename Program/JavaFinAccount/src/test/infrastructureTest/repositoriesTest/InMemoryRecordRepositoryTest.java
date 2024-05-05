package infrastructureTest.repositoriesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for InMemoryRecordRepository
 */
class InMemoryRecordRepositoryTest {
    private InMemoryRecordRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRecordRepository();
    }

    @Test
    @DisplayName("Add a record and retrieve it")
    void testAddAndRetrieveRecord() throws InvalidAmountException, InvalidCategoryException {
        Record record = new Record(1, 100.00, "Test Description", new DateAndTime(), new Category("Utilities"), RecordType.EXPENSE);
        repository.addRecord(record);

        Optional<Record> foundRecord = repository.findById(1L);
        assertTrue(foundRecord.isPresent(), "Record should be found after being added.");
        assertEquals(record, foundRecord.get(), "The retrieved record should match the added record.");
    }

    @Test
    @DisplayName("Delete a record and ensure it is removed")
    void testDeleteRecord() throws InvalidAmountException, InvalidCategoryException {
        Record record = new Record(2, 200.00, "Test Expense", new DateAndTime(), new Category("Transportation"), RecordType.EXPENSE);
        repository.addRecord(record);

        repository.deleteRecord(record);
        Optional<Record> foundRecord = repository.findById(2L);
        assertFalse(foundRecord.isPresent(), "Record should not be found after being deleted.");
    }

    @Test
    @DisplayName("Retrieve all records and verify contents")
    void testRetrieveAllRecords() throws InvalidAmountException, InvalidCategoryException {
        Record record1 = new Record(1, 332.00, "Sale", new DateAndTime(), new Category("Sales"), RecordType.INCOME);
        Record record2 = new Record(2, 110.20, "Rent", new DateAndTime(), new Category("Rent"), RecordType.EXPENSE);
        repository.addRecord(record1);
        repository.addRecord(record2);

        Iterable<Record> allRecords = repository.findAll();
        assertNotNull(allRecords, "findAll should not return null.");
        assertTrue(allRecords.iterator().hasNext(), "findAll should include all records.");
    }

    @Test
    @DisplayName("Attempt to create a record with an invalid amount")
    void testCreateRecordWithInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> {
            repository.createRecord(4, -100.00, "Error Test", new DateAndTime(), new Category("Error"), RecordType.EXPENSE);
        }, "Creating a record with a negative amount should throw an InvalidAmountException.");
    }

    @Test
    @DisplayName("Check getLastId for correct increment")
    void testGetLastId() throws InvalidAmountException, InvalidCategoryException {
        assertEquals(0, repository.getLastId(), "Initial last ID should be zero.");

        repository.addRecord(new Record(1, 100.00, "Test Record", new DateAndTime(), new Category("Test"), RecordType.EXPENSE));
        assertEquals(1, repository.getLastId(), "Last ID should increment after adding a record.");
    }
}
