package applicationTest.servicesTest;

import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the RecordService class.
 */
class RecordServiceTest {
    private RecordRepositoryInterface recordRepository;
    private RecordService recordService;

    @BeforeEach
    void setUp() {
        recordRepository = mock(RecordRepositoryInterface.class);
        recordService = new RecordService(recordRepository);
    }

    @Test
    @DisplayName("addRecord should return the added record")
    void addRecord_ShouldReturnAddedRecord() throws InvalidAmountException, InvalidCategoryException {
        // Prepare DateAndTime and Category mocks to prevent constructor issues
        DateAndTime dateAndTime = mock(DateAndTime.class);
        Category category = new Category("Utilities"); // Assuming Category constructor is not problematic

        Record record = new Record(1L, 100.0, "Test Description", dateAndTime, category, RecordType.EXPENSE);
        when(recordRepository.addRecord(any(Record.class))).thenReturn(record);

        Record addedRecord = recordService.addRecord(record);
        assertNotNull(addedRecord, "Record should not be null");
        assertEquals(record, addedRecord, "Expected returned record to be the same as the added record");
    }

    @Test
    @DisplayName("deleteRecord should call repository delete method")
    void deleteRecord_ShouldCallRepositoryDelete() throws InvalidCategoryException, InvalidAmountException {
        Record record = new Record(1L, 100.0, "Test Description", new DateAndTime(), new Category("Utilities"), RecordType.EXPENSE);
        doNothing().when(recordRepository).deleteRecord(record);

        recordService.deleteRecord(record);
        verify(recordRepository).deleteRecord(record);
    }

    @Test
    @DisplayName("getRecord should return a record by ID")
    void getRecord_ShouldReturnRecordById() throws InvalidCategoryException, InvalidAmountException {
        Long id = 1L;
        Record record = new Record(id, 100.0, "Test Description", new DateAndTime(), new Category("Utilities"), RecordType.EXPENSE);
        when(recordRepository.findById(id)).thenReturn(Optional.of(record));

        Optional<Record> foundRecord = recordService.getRecord(id);
        assertTrue(foundRecord.isPresent());
        assertEquals(record, foundRecord.get());
    }

    @Test
    @DisplayName("getAllRecords should return all records")
    void getAllRecords_ShouldReturnAllRecords() throws InvalidCategoryException, InvalidAmountException {
        Iterable<Record> records = List.of(
                new Record(1L, 100.0, "Description One", new DateAndTime(), new Category("Utilities"), RecordType.EXPENSE),
                new Record(2L, 200.0, "Description Two", new DateAndTime(), new Category("Sales"), RecordType.INCOME)
        );
        when(recordRepository.findAll()).thenReturn(records);

        Iterable<Record> allRecords = recordService.getAllRecords();
        assertNotNull(allRecords);
        assertEquals(records, allRecords);
    }
}
