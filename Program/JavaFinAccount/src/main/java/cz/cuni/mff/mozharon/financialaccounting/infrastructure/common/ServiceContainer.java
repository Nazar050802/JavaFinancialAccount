package cz.cuni.mff.mozharon.financialaccounting.infrastructure.common;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;

public class ServiceContainer {
    private UserService userService;
    private RecordService recordService;
    private CategoryService categoryService;

    public ServiceContainer(UserService userService, RecordService recordService, CategoryService categoryService) {
        this.userService = userService;
        this.recordService = recordService;
        this.categoryService = categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    public RecordService getRecordService() {
        return recordService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
}