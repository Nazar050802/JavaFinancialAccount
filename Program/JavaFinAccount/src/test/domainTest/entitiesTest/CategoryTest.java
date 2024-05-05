package domainTest.entitiesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() throws InvalidCategoryException {
        // Initialize Category object for each test
        category = new Category("Utilities");
    }

    @Test
    @DisplayName("Test Category can be created with a valid name")
    void testCategoryCreationValidName() throws InvalidCategoryException {
        // Test constructor with valid initial name
        assertEquals("Utilities", category.getName());
    }

    @Test
    @DisplayName("Test Category creation fails with too long name")
    void testCategoryCreationInvalidName() {
        // Test constructor exception with too long name
        assertThrows(InvalidCategoryException.class, () -> {
            new Category("This is a very long category name that should fail");
        });
    }

    @Test
    @DisplayName("Test getName returns correct name")
    void testGetName() {
        // Check that getName returns the expected name
        assertEquals("Utilities", category.getName());
    }

    @Test
    @DisplayName("Test setName with a valid name")
    void testSetNameValid() throws InvalidCategoryException {
        // Set a new name within valid range
        category.setName("Groceries");
        assertEquals("Groceries", category.getName());
    }

    @Test
    @DisplayName("Test setName fails with too long name")
    void testSetNameInvalid() {
        // Attempt to set a name that is too long, expecting an exception
        assertThrows(InvalidCategoryException.class, () -> {
            category.setName("This is a very long category name that should fail");
        });
    }

    @Test
    @DisplayName("Test default constructor sets no name")
    void testDefaultConstructor() {
        // Test the default constructor for no initial name set
        Category emptyCategory = new Category();
        assertNull(emptyCategory.getName());
    }
}
