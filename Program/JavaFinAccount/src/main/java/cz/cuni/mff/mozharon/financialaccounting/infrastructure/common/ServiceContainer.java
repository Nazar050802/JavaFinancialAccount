package cz.cuni.mff.mozharon.financialaccounting.infrastructure.common;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;

/**
 * Container for centralizing the management of service instances within the application.
 * This class provides a unified access point for retrieving various services that are used
 * throughout the application, facilitating easy management of dependencies.
 */
public class ServiceContainer {
    private UserService userService;
    private RecordService recordService;
    private CategoryService categoryService;
    private StatisticsService statisticsService;

    /**
     * Constructs a new ServiceContainer with the specified services.
     *
     * @param userService The user service to manage user-related operations.
     * @param recordService The record service to manage financial records.
     * @param categoryService The category service to manage categories of financial records.
     * @param statisticsService The statistics service to manage computation and retrieval of statistical data.
     */
    public ServiceContainer(UserService userService, RecordService recordService, CategoryService categoryService, StatisticsService statisticsService) {
        this.userService = userService;
        this.recordService = recordService;
        this.categoryService = categoryService;
        this.statisticsService = statisticsService;
    }

    /**
     * Retrieves the UserService instance contained within this service container.
     * @return The UserService instance.
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Retrieves the RecordService instance contained within this service container.
     * @return The RecordService instance.
     */
    public RecordService getRecordService() {
        return recordService;
    }

    /**
     * Retrieves the CategoryService instance contained within this service container.
     * @return The CategoryService instance.
     */
    public CategoryService getCategoryService() {
        return categoryService;
    }

    /**
     * Retrieves the StatisticsService instance contained within this service container.
     * @return The StatisticsService instance.
     */
    public StatisticsService getStatisticsService() {
        return statisticsService;
    }
}