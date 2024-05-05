package cz.cuni.mff.mozharon.financialaccounting.application.config;

import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;

public class ServiceConfig {
    private final UserRepositoryInterface userRepository;
    private final RecordRepositoryInterface recordRepository;
    private final CategoryRepositoryInterface categoryRepository;

    public ServiceConfig(UserRepositoryInterface userRepository, RecordRepositoryInterface recordRepository, CategoryRepositoryInterface categoryRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.categoryRepository = categoryRepository;
    }

    public UserService createUserService() {
        return new UserService(userRepository);
    }

    public RecordService createRecordService() {
        return new RecordService(recordRepository);
    }

    public CategoryService createCategoryService() {
        return new CategoryService(categoryRepository);
    }

    public StatisticsService createStatisticsService() {
        return new StatisticsService();
    }
}
