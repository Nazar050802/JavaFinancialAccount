/**
 * Provides business logic functions related to categories in the application.
 */

package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;

/**
 * Provides business logic functions related to categories in the application.
 */
public class CategoryService {
    private final CategoryRepositoryInterface categoryRepositoryInterface;

    /**
     * Constructs a new CategoryService with a given category repository interface.
     *
     * @param categoryRepositoryInterface The category repository interface to be used by this service
     */
    public CategoryService(CategoryRepositoryInterface categoryRepositoryInterface) {
        this.categoryRepositoryInterface = categoryRepositoryInterface;
    }

    /**
     * Adds a new category to the repository.
     *
     * @param category The category to be added
     */
    public void addCategory(Category category) {
        categoryRepositoryInterface.addCategory(category);
    }

    /**
     * Retrieves a category by name.
     *
     * @param name The name of the category to retrieve
     * @return An Optional containing the found category or an empty Optional if no category is found
     */
    public Optional<Category> getCategory(String name) {
        return categoryRepositoryInterface.findCategoryByName(name);
    }

    /**
     * Deletes a category by name.
     *
     * @param name The name of the category to be deleted
     */
    public void deleteCategory(String name) {
        categoryRepositoryInterface.deleteCategoryByName(name);
    }

    /**
     * Retrieves all categories from the repository.
     *
     * @return An iterable of all categories
     */
    public Iterable<Category> getAllCategories() {
        return categoryRepositoryInterface.findAll();
    }

    /**
     * Creates a new category with the given name.
     *
     * @param name The name of the category to be created
     * @return The newly created category
     * @throws InvalidCategoryException If the category name is invalid
     */
    public Category createCategory(String name) throws InvalidCategoryException {
        return categoryRepositoryInterface.createCategory(name);
    }
}
