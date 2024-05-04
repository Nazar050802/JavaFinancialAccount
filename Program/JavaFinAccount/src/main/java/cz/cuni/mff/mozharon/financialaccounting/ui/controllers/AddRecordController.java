package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;

import java.util.Objects;
import java.util.Optional;

public class AddRecordController {
    ControllerCore controllerCore;

    public AddRecordController(ControllerCore controllerCore) {
        this.controllerCore = controllerCore;
    }

    public boolean checkCategoryExists(String categoryName) {
        return controllerCore.serviceContainer.getCategoryService().getCategory(categoryName).isPresent();
    }

    public void addCategory(String categoryName) throws InvalidCategoryException {
        controllerCore.serviceContainer.getCategoryService().createCategory(categoryName);
    }

    public void addRecord(Double amount, String description, String dateAndTime, String category, String recordType) throws InvalidAmountException, InvalidCategoryException {
        Category categoryForUse = getCategory(category);

        controllerCore.serviceContainer.getRecordService().createRecord(amount, description, dateAndTime, categoryForUse, recordType);
    }

    private Category getCategory(String category) throws InvalidCategoryException {
        return getOrCreateCategory(category);
    }

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
