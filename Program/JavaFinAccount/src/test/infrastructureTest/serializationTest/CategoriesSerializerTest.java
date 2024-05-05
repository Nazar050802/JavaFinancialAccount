package infrastructureTest.serializationTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.CategoriesSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CategoriesSerializer class.
 */
class CategoriesSerializerTest {
    private CategoriesSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new CategoriesSerializer();
    }

    @Test
    @DisplayName("Serialize should return correct format")
    void testSerialize() throws InvalidCategoryException {
        Category category = new Category("Education");
        String expected = "CATEGORY|Education";
        String actual = serializer.serialize(category);
        assertEquals(expected, actual, "Serialized data should match expected format.");
    }

    @Test
    @DisplayName("Deserialize should return correct Category object")
    void testDeserialize() throws InvalidCategoryException {
        String data = "CATEGORY|Health";
        Category category = serializer.deserialize(data);
        assertNotNull(category, "Deserialized category should not be null.");
        assertEquals("Health", category.getName(), "Deserialized category name should match input data.");
    }
}
