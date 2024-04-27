package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;

import java.util.Optional;

public class CategoryService {

    private final CategoryRepositoryInterface categoryRepositoryInterface;

    public CategoryService(CategoryRepositoryInterface CategoryRepositoryInterface) {
        this.categoryRepositoryInterface = CategoryRepositoryInterface;
    }

    public void addCategory(Category category) {
        categoryRepositoryInterface.addCategory(category);
    }

    public void addSubCategoryToCategoryByName(String categoryName, Category subCategory) throws InvalidCategoryException {
        categoryRepositoryInterface.addSubCategoryToCategoryByName(categoryName, subCategory);
    }

    public Optional<Category> getCategory(String name) {
        return categoryRepositoryInterface.findCategoryByName(name);
    }

    public void deleteSubCategoryFromCategoryByName(String categoryName, String subCategoryName){
        categoryRepositoryInterface.deleteSubCategoryFromCategoryByName(categoryName, subCategoryName);
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
