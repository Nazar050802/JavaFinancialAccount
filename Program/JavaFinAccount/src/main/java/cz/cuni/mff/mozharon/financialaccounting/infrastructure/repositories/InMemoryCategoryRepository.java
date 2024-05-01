package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCategoryRepository implements CategoryRepositoryInterface {
    private final ConcurrentHashMap<String, Category> categories = new ConcurrentHashMap<>();

    @Override
    public Category addCategory(Category category) {
        return categories.put(category.getName(), category);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return Optional.ofNullable(categories.get(name));
    }

    @Override
    public void deleteCategoryByName(String name) {
        categories.remove(name);
    }

    @Override
    public Iterable<Category> findAll() {
        return categories.values();
    }

    @Override
    public Category createCategory(String name) throws InvalidCategoryException {
        return addCategory(new Category(name));
    }
}
