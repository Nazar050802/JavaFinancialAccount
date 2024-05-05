package infrastructureTest.repositoriesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryCategoryRepository;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

/**
 * Test suite for InMemoryCategoryRepository
 */
class InMemoryCategoryRepositoryTest {
    private InMemoryCategoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCategoryRepository();
    }

    @Test
    @DisplayName("Add new category and retrieve it")
    void testAddAndRetrieveCategory() throws InvalidCategoryException {
        Category category = new Category("Utilities");
        repository.addCategory(category);

        Optional<Category> foundCategory = repository.findCategoryByName("Utilities");
        assertTrue(foundCategory.isPresent(), "Category should be found after being added.");
        assertEquals(category, foundCategory.get(), "Retrieved category should match the added category.");
    }

    @Test
    @DisplayName("Delete a category and ensure it's removed")
    void testDeleteCategory() throws InvalidCategoryException {
        Category category = new Category("Groceries");
        repository.addCategory(category);

        repository.deleteCategoryByName("Groceries");
        Optional<Category> foundCategory = repository.findCategoryByName("Groceries");
        assertFalse(foundCategory.isPresent(), "Category should not be found after being deleted.");
    }

    @Test
    @DisplayName("Ensure findAll returns all categories")
    void testFindAllCategories() throws InvalidCategoryException {
        repository.addCategory(new Category("Entertainment"));
        repository.addCategory(new Category("Education"));

        Iterable<Category> categories = repository.findAll();
        assertNotNull(categories, "findAll should not return null.");
        assertTrue(categories.iterator().hasNext(), "findAll should return all added categories.");
    }

    @Test
    @DisplayName("Attempt to add a duplicate category")
    void testAddDuplicateCategory() throws InvalidCategoryException {
        Category category1 = new Category("Fitness");
        repository.addCategory(category1);

        Category category2 = new Category("Fitness");
        repository.addCategory(category2);

        Iterable<Category> categories = repository.findAll();
        int count = 0;
        for (Category c : categories) {
            if (c.getName().equals("Fitness")) {
                count++;
            }
        }
        assertEquals(1, count, "Should only store one instance of a category with the same name.");
    }
}
