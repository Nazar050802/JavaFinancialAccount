package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Optional;

/**
 * Controller responsible for handling the addition of records and categories to the system.
 */
public class AddRecordController {
    ControllerCore controllerCore;

    /**
     * Constructs a new AddRecordController with a reference to the ControllerCore.
     * @param controllerCore the core controller containing service connections.
     */
    public AddRecordController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    /**
     * Checks if a category exists within the system.
     * @param categoryName the name of the category to check.
     * @return true if the category exists, false otherwise.
     */
    public boolean checkCategoryExists(String categoryName) {
        return controllerCore.serviceContainer.getCategoryService().getCategory(categoryName).isPresent();
    }

    /**
     * Adds a new category to the system.
     * @param categoryName the name of the category to be added.
     * @throws InvalidCategoryException if the category cannot be created due to validation issues.
     */
    public void addCategory(String categoryName) throws InvalidCategoryException {
        controllerCore.serviceContainer.getCategoryService().createCategory(categoryName);
    }

    /**
     * Adds a new record to the system.
     * @param amount the amount of the transaction.
     * @param description the description of the record.
     * @param dateAndTime the date and time of the transaction.
     * @param category the name of the category this record belongs to.
     * @param recordType the type of the record (e.g., INCOME, EXPENSE).
     * @throws InvalidAmountException if the amount is invalid.
     * @throws InvalidCategoryException if the category does not exist.
     */
    public void addRecord(Double amount, String description, String dateAndTime, String category, String recordType) throws InvalidAmountException, InvalidCategoryException {
        Category categoryForUse = getCategory(category);

        controllerCore.serviceContainer.getRecordService().createRecord(amount, description, dateAndTime, categoryForUse, recordType);
    }

    /**
     * Retrieves a Category object from the system by its name. If the category does not exist,
     * it attempts to create it.
     *
     * @param category The name of the category to retrieve or create.
     * @return The retrieved or newly created Category object.
     * @throws InvalidCategoryException If there is an error in creating the category due to invalid input.
     */
    private Category getCategory(String category) throws InvalidCategoryException {
        return getOrCreateCategory(category);
    }

    /**
     * Retrieves a Category object from the system by its name. If the category does not exist,
     * this method creates a new Category with the given name.
     *
     * @param category The name of the category to retrieve or create.
     * @return The Category object associated with the given name.
     * @throws InvalidCategoryException If the category cannot be created due to validation issues or other constraints.
     */
    private Category getOrCreateCategory(String category) throws InvalidCategoryException {
        Optional<Category> categoryOpt = controllerCore.serviceContainer.getCategoryService().getCategory(category);
        if (categoryOpt.isPresent()) {
            return categoryOpt.get();
        } else {
            try {
                return controllerCore.serviceContainer.getCategoryService().createCategory(category);
            } catch (InvalidCategoryException e) {
                throw e;
            }
        }
    }

    /**
     * Attempts to save data to the persistent storage.
     * @return true if the save operation was successful, false otherwise.
     */
    public boolean saveData(){
        try {
            controllerCore.tryToSaveDataToFile();
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

}
