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
        return "CATEGORY" + "|" + SerializerUtils.cleanseField(category.getName()) + "|" + getSubcategoriesString(category.getSubcategoriesCopy());
    }

    private String getSubcategoriesString(List<Category> subCategories){
        StringBuilder allSubCategories = new StringBuilder();
        for (int i = 0; i < subCategories.size(); i++) {
            Category subCategory = subCategories.get(i);

            allSubCategories.append(SerializerUtils.cleanseField(subCategory.getName()));
            if(i + 1 != subCategories.size()){
                allSubCategories.append("|");
            }
        }

        return !subCategories.isEmpty() ? "SUB_CATEGORIES" + "|" + allSubCategories: "";
    }

    @Override
    public Category deserialize(String data) throws InvalidCategoryException {

        final int numberToStartWith = 1; // Also number of help words before first real data
        String[] parts = data.split("\\|");

        List<Category> subCategories = new ArrayList<>();
        if(parts.length > numberToStartWith + 1){
            for(int i = numberToStartWith + 2; i < parts.length; i++){
                Category subCategory = new Category(parts[i]);

                subCategories.add(subCategory);
            }
        }

        return new Category(parts[numberToStartWith], subCategories);

    }
}
