package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Optional;

public interface CategoryRepositoryInterface {
    void addCategory(Category category);
    void addSubCategoryToCategoryByName(String categoryName, Category subCategory) throws InvalidCategoryException;
    Optional<Category> findCategoryByName(String name);
    void deleteCategoryByName(String name);
    void deleteSubCategoryFromCategoryByName(String categoryName, String subCategoryName);
    Iterable<Category> findAll();
}
