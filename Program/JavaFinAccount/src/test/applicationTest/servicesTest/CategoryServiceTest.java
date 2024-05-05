package applicationTest.servicesTest;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the CategoryService class.
 */
class CategoryServiceTest {
    private CategoryService categoryService;
    private CategoryRepositoryInterface categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepositoryInterface.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    @DisplayName("addCategory should delegate to categoryRepository")
    void addCategory_ShouldDelegateToRepository() throws InvalidCategoryException {
        Category category = new Category("Books");
        categoryService.addCategory(category);
        verify(categoryRepository, times(1)).addCategory(category);
    }

    @Test
    @DisplayName("getCategory should return a category when it exists")
    void getCategory_ShouldReturnCategoryWhenExists() throws InvalidCategoryException {
        String name = "Books";
        Optional<Category> expected = Optional.of(new Category(name));
        when(categoryRepository.findCategoryByName(name)).thenReturn(expected);

        Optional<Category> result = categoryService.getCategory(name);
        assertTrue(result.isPresent());
        assertEquals(expected.get(), result.get());
    }

    @Test
    @DisplayName("getCategory should return empty when no category exists")
    void getCategory_ShouldReturnEmptyWhenNoCategoryExists() {
        String name = "Nonexistent";
        when(categoryRepository.findCategoryByName(name)).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.getCategory(name);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("deleteCategory should delegate to categoryRepository")
    void deleteCategory_ShouldDelegateToRepository() {
        String name = "Books";
        categoryService.deleteCategory(name);
        verify(categoryRepository, times(1)).deleteCategoryByName(name);
    }

    @Test
    @DisplayName("getAllCategories should return all categories")
    void getAllCategories_ShouldReturnAllCategories() throws InvalidCategoryException {
        Iterable<Category> expected = List.of(new Category("Books"), new Category("Electronics"));
        when(categoryRepository.findAll()).thenReturn(expected);

        Iterable<Category> result = categoryService.getAllCategories();
        assertIterableEquals(expected, result);
    }

    @Test
    @DisplayName("createCategory should return a new category")
    void createCategory_ShouldReturnNewCategory() throws InvalidCategoryException {
        String name = "Books";
        Category expected = new Category(name);
        when(categoryRepository.createCategory(name)).thenReturn(expected);

        Category result = categoryService.createCategory(name);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("createCategory should throw InvalidCategoryException for invalid names")
    void createCategory_ShouldThrowForInvalidNames() throws InvalidCategoryException {
        String name = "";  // Assuming empty name is invalid
        when(categoryRepository.createCategory(name)).thenThrow(InvalidCategoryException.class);

        assertThrows(InvalidCategoryException.class, () -> categoryService.createCategory(name));
    }
}
