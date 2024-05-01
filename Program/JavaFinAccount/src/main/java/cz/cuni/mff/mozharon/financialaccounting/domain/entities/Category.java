package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

public class Category {
    private String name;
    private final static int maxNameLength = 30;

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidCategoryException {
        if (name.length() <= maxNameLength) {
            this.name = name;
        } else {
            throw new InvalidCategoryException("Maximum category name length was exceeded.");
        }
    }

    public Category() {}

    public Category(String name) throws InvalidCategoryException {
        setName(name);
    }
}
