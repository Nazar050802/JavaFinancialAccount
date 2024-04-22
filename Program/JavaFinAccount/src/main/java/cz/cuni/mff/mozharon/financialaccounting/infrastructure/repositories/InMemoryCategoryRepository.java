package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCategoryRepository implements CategoryRepositoryInterface {
    private final ConcurrentHashMap<String, Category> categories = new ConcurrentHashMap<>();

    @Override
    public void addCategory(Category category) {
        categories.put(category.getName(), category);
    }

    @Override
    public void addSubCategoryToCategoryByName(String categoryName, Category subCategory) throws InvalidCategoryException {
        Optional<Category> optionalCategory = findCategoryByName(categoryName);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.addSubcategory(subCategory);
        }
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
    public void deleteSubCategoryFromCategoryByName(String categoryName, String subCategoryName) {
        Optional<Category> optionalCategory = findCategoryByName(categoryName);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.getSubcategories().removeIf(sub -> sub.getName().equals(subCategoryName));
        }
    }

    @Override
    public Iterable<Category> findAll() {
        return categories.values();
    }
}
