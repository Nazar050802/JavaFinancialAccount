package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

/**
 * Represents a category of financial records in the accounting system.
 */
public class Category {
    private String name;
    private final static int maxNameLength = 30;

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category ensuring it does not exceed the maximum allowed length.
     *
     * @param name The name to set for the category.
     * @throws InvalidCategoryException if the name length exceeds the maximum limit.
     */
    public void setName(String name) throws InvalidCategoryException {
        if (name.length() <= maxNameLength) {
            this.name = name;
        } else {
            throw new InvalidCategoryException("Maximum category name length was exceeded.");
        }
    }

    /**
     * Default constructor for creating a category with no initial name.
     */
    public Category() {}

    /**
     * Constructor for creating a category with a specified name.
     *
     * @param name The name of the category.
     * @throws InvalidCategoryException if the name length exceeds the maximum limit.
     */
    public Category(String name) throws InvalidCategoryException {
        setName(name);
    }
}
