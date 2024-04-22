package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Category  {

    private String name;
    private final static int maxSizeOfSubCategories = 40;
    private final static int maxNameLength = 30;
    private List<Category> subcategories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Category> getSubcategoriesCopy() {
        return new ArrayList<>(subcategories); // Return a copy to avoid external modification
    }

    public List<Category> getSubcategories() {
        return this.subcategories;
    }

    public void setSubcategories(List<Category> subcategories){
        this.subcategories = subcategories;
    }

    public void setName(String name) throws InvalidCategoryException {
        if(name.length() <= maxNameLength){
            this.name = name;
        }
        else {
           throw new InvalidCategoryException("Maximum category length name was reached.");
        }
    }


    public Category(){}

    public Category(String name) throws InvalidCategoryException {
        setName(name);
    }

    public Category(String name, List<Category> subcategories) throws InvalidCategoryException {
        setName(name);
        setSubcategories(subcategories);
    }


    // Method to add a subcategory
    public void addSubcategory(Category subcategory) throws InvalidCategoryException {
        if (subcategory != null && !this.subcategories.contains(subcategory) && this.subcategories.size() + 1 <= maxSizeOfSubCategories) {
            this.subcategories.add(subcategory);
        }
        else if(this.subcategories.size() + 1 <= maxSizeOfSubCategories){
            throw new InvalidCategoryException("Maximum size of subcategory was reached.");
        }
    }

    // Method to remove a subcategory
    public void removeSubcategory(Category subcategory) {
        this.subcategories.remove(subcategory);
    }

    // Remove a subcategory by its name
    public void removeSubcategoryByName(String subcategoryName) {
        if (subcategoryName == null) {
            return;
        }
        Iterator<Category> iterator = subcategories.iterator();
        while (iterator.hasNext()) {
            Category subcategory = iterator.next();
            if (subcategoryName.equals(subcategory.getName())) {
                iterator.remove();  // Safely remove the subcategory
                break;  // Stop the loop once the subcategory is found and removed
            }
        }
    }
}
