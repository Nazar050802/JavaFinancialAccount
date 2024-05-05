package infrastructureTest.serializationTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidDateException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidTimeException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.RecordSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RecordSerializer.
 */
public class RecordSerializerTest {
    private RecordSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new RecordSerializer();
    }

    @Test
    @DisplayName("Serialize should return correct format")
    void testSerialize() throws InvalidDateException, InvalidTimeException, InvalidCategoryException, InvalidAmountException {
        DateAndTime dateAndTime = new DateAndTime();
        dateAndTime.setDay(1);
        dateAndTime.setMonth(1);
        dateAndTime.setYear(2020);
        dateAndTime.setHours(12);
        dateAndTime.setMinutes(0);
        dateAndTime.setSeconds(0);

        Record record = new Record(1L, 200.00, "Salary", dateAndTime, new Category("Income"), RecordType.INCOME);
        String expected = "RECORD|1|200.0|Salary|1 1 2020 0 0 12|Income|INCOME";
        String actual = serializer.serialize(record);
        assertEquals(expected, actual, "Serialized data should match expected format.");
    }

    @Test
    @DisplayName("Deserialize should return correct Record object")
    void testDeserialize() throws Exception {
        String data = "RECORD|1|200.0|Salary|1 1 2020 0 0 12|Income|INCOME";
        Record record = serializer.deserialize(data);
        assertNotNull(record, "Deserialized record should not be null.");
        assertEquals(1, record.getId());
        assertEquals(200.0, record.getAmount());
        assertEquals("Salary", record.getDescription());
        assertEquals("Income", record.getCategory().getName());
        assertEquals(RecordType.INCOME, record.getRecordType());
        assertEquals(2020, record.getDateAndTime().getYear());
    }
}
