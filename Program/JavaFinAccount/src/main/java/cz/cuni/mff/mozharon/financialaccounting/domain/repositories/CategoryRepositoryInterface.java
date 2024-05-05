package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Optional;

/**
 * Interface defining the operations for managing categories within the application.
 * This includes operations for creating, finding, adding, and deleting categories.
 */
public interface CategoryRepositoryInterface {
    /**
     * Adds a new category to the repository.
     *
     * @param category The category to add.
     * @return The added category.
     */
    Category addCategory(Category category);

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category to find.
     * @return An Optional containing the found category, or an empty Optional if no category is found.
     */
    Optional<Category> findCategoryByName(String name);

    /**
     * Deletes a category from the repository based on its name.
     *
     * @param name The name of the category to be deleted.
     */
    void deleteCategoryByName(String name);

    /**
     * Retrieves all categories in the repository.
     *
     * @return An Iterable collection of all categories.
     */
    Iterable<Category> findAll();

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category.
     * @return The newly created category.
     * @throws InvalidCategoryException If the category creation fails due to invalid data.
     */
    Category createCategory(String name) throws InvalidCategoryException;
}
