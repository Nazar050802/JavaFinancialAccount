package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.SerializerUtils;

/**
 * Serializer for Category objects, implementing the SerializerInterface.
 */
public class CategoriesSerializer implements SerializerInterface<Category> {
    public static final String keyWord = "CATEGORY";

    /**
     * Serializes a Category object into a string representation.
     *
     * @param category the category to serialize
     * @return the serialized string representation of the category
     */
    @Override
    public String serialize(Category category) {
        return keyWord + "|" + SerializerUtils.cleanseField(category.getName());
    }

    /**
     * Deserializes a string back into a Category object.
     *
     * @param data the string to deserialize
     * @return the deserialized Category object
     * @throws InvalidCategoryException if the category data is not valid
     */
    @Override
    public Category deserialize(String data) throws InvalidCategoryException {

        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        return new Category(parts[numberToStartWith]);

    }
}
