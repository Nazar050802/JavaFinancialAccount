package domainTest.entitiesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    private Record record;
    private Category category;
    private DateAndTime dateAndTime;

    @BeforeEach
    void setUp() throws InvalidAmountException {
        category = Mockito.mock(Category.class);
        dateAndTime = Mockito.mock(DateAndTime.class);
        record = new Record(1.0, "Description", dateAndTime, category, RecordType.EXPENSE);
    }

    @Test
    @DisplayName("Successfully create a record")
    void testCreateRecordSuccessfully() {
        assertAll("Verify all properties are set correctly",
                () -> assertEquals(1.0, record.getAmount()),
                () -> assertEquals("Description", record.getDescription()),
                () -> assertSame(dateAndTime, record.getDateAndTime()),
                () -> assertSame(category, record.getCategory()),
                () -> assertEquals(RecordType.EXPENSE, record.getRecordType()),
                () -> assertTrue(record.getId() > 0)
        );
    }

    @Test
    @DisplayName("Fail to create a record with non-positive amount")
    void testCreateRecordNonPositiveAmount() {
        InvalidAmountException thrown = assertThrows(InvalidAmountException.class, () ->
                new Record(-1.0, "Description", dateAndTime, category, RecordType.INCOME));
        assertTrue(thrown.getMessage().contains("The amount has invalid value"));
    }

    @Test
    @DisplayName("Record ID should be unique and incrementing")
    void testRecordIDUniqueAndIncrementing() throws InvalidAmountException {
        Record firstRecord = new Record(1.0, "First", dateAndTime, category, RecordType.INCOME);
        Record secondRecord = new Record(2.0, "Second", dateAndTime, category, RecordType.EXPENSE);
        assertNotEquals(firstRecord.getId(), secondRecord.getId());
        assertTrue(firstRecord.getId() < secondRecord.getId());
    }

    @Test
    @DisplayName("Test setting a record with predefined ID")
    void testSetPredefinedId() throws InvalidAmountException {
        Record recordWithId = new Record(100, 1.0, "With ID", dateAndTime, category, RecordType.EXPENSE);
        assertEquals(100, recordWithId.getId());
    }

}