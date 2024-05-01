package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Optional;

public interface CategoryRepositoryInterface {
    Category addCategory(Category category);
    Optional<Category> findCategoryByName(String name);
    void deleteCategoryByName(String name);
    Iterable<Category> findAll();
    Category createCategory(String name) throws InvalidCategoryException;
}
