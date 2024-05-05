package cz.cuni.mff.mozharon.financialaccounting.application.config;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;

/**
 * Configuration class for service layer dependencies.
 * This class handles the construction and dependency injection of service layer components.
 */
public class ServiceConfig {
    private final UserRepositoryInterface userRepository;
    private final RecordRepositoryInterface recordRepository;
    private final CategoryRepositoryInterface categoryRepository;

    /**
     * Constructs a new ServiceConfig instance with specified repository interfaces.
     *
     * @param userRepository     The user repository interface
     * @param recordRepository   The record repository interface
     * @param categoryRepository The category repository interface
     */
    public ServiceConfig(UserRepositoryInterface userRepository, RecordRepositoryInterface recordRepository, CategoryRepositoryInterface categoryRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Creates a UserService instance with configured dependencies.
     *
     * @return A new instance of UserService
     */
    public UserService createUserService() {
        return new UserService(userRepository);
    }

    /**
     * Creates a RecordService instance with configured dependencies.
     *
     * @return A new instance of RecordService
     */
    public RecordService createRecordService() {
        return new RecordService(recordRepository);
    }

    /**
     * Creates a CategoryService instance with configured dependencies.
     *
     * @return A new instance of CategoryService
     */
    public CategoryService createCategoryService() {
        return new CategoryService(categoryRepository);
    }

    /**
     * Creates a StatisticsService instance without specific repository dependencies.
     *
     * @return A new instance of StatisticsService
     */
    public StatisticsService createStatisticsService() {
        return new StatisticsService();
    }
}
