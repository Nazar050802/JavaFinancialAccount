package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.SerializerUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoriesSerializer implements SerializerInterface<Category> {
    public String keyWord = "CATEGORY";

    @Override
    public String serialize(Category category) {
        return keyWord + "|" + SerializerUtils.cleanseField(category.getName());
    }

    @Override
    public Category deserialize(String data) throws InvalidCategoryException {

        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        return new Category(parts[numberToStartWith]);

    }
}
