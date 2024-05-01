package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;

public class CategoryService {
    private final CategoryRepositoryInterface categoryRepositoryInterface;

    public CategoryService(CategoryRepositoryInterface categoryRepositoryInterface) {
        this.categoryRepositoryInterface = categoryRepositoryInterface;
    }

    public void addCategory(Category category) {
        categoryRepositoryInterface.addCategory(category);
    }

    public Optional<Category> getCategory(String name) {
        return categoryRepositoryInterface.findCategoryByName(name);
    }

    public void deleteCategory(String name) {
        categoryRepositoryInterface.deleteCategoryByName(name);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepositoryInterface.findAll();
    }

    public Category createCategory(String name) throws InvalidCategoryException {
        return categoryRepositoryInterface.createCategory(name);
    }
}
