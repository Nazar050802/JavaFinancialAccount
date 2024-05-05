package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the CategoryRepositoryInterface, managing records storage.
 */
public class InMemoryCategoryRepository implements CategoryRepositoryInterface {
    private final ConcurrentHashMap<String, Category> categories = new ConcurrentHashMap<>();

    /**
     * Adds a new category to the repository.
     *
     * @param category The category to add.
     * @return The category that was added to the repository.
     */
    @Override
    public Category addCategory(Category category) {
        return categories.put(category.getName(), category);
    }

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category to find.
     * @return An Optional containing the found category, or an empty Optional if no category is found.
     */
    @Override
    public Optional<Category> findCategoryByName(String name) {
        return Optional.ofNullable(categories.get(name));
    }

    /**
     * Deletes a category by its name.
     *
     * @param name The name of the category to delete.
     */
    @Override
    public void deleteCategoryByName(String name) {
        categories.remove(name);
    }

    /**
     * Returns all categories in the repository.
     *
     * @return An iterable collection of all categories.
     */
    @Override
    public Iterable<Category> findAll() {
        return categories.values();
    }

    /**
     * Creates and adds a new category with the specified name to the repository.
     *
     * @param name The name of the new category.
     * @return The newly created category.
     * @throws InvalidCategoryException If the category name fails validation checks.
     */
    @Override
    public Category createCategory(String name) throws InvalidCategoryException {
        return addCategory(new Category(name));
    }
}
