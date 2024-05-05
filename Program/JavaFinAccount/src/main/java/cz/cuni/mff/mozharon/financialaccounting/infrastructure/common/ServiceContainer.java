package cz.cuni.mff.mozharon.financialaccounting.infrastructure.common;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;

public class ServiceContainer {
    private UserService userService;
    private RecordService recordService;
    private CategoryService categoryService;
    private StatisticsService statisticsService;

    public ServiceContainer(UserService userService, RecordService recordService, CategoryService categoryService, StatisticsService statisticsService) {
        this.userService = userService;
        this.recordService = recordService;
        this.categoryService = categoryService;
        this.statisticsService = statisticsService;
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

    public StatisticsService getStatisticsService() {
        return statisticsService;
    }
}