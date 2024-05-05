package applicationTest.configTest;

import cz.cuni.mff.mozharon.financialaccounting.application.config.ServiceConfig;
import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.CategoryRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Tests for the ServiceConfig class.
 */
public class ServiceConfigTest {
    private UserRepositoryInterface userRepository;
    private RecordRepositoryInterface recordRepository;
    private CategoryRepositoryInterface categoryRepository;
    private ServiceConfig serviceConfig;

    @BeforeEach
    void setUp() {
        // Mock the repository interfaces
        userRepository = mock(UserRepositoryInterface.class);
        recordRepository = mock(RecordRepositoryInterface.class);
        categoryRepository = mock(CategoryRepositoryInterface.class);

        // Initialize ServiceConfig with the mocked repositories
        serviceConfig = new ServiceConfig(userRepository, recordRepository, categoryRepository);
    }

    @Test
    @DisplayName("createUserService should return a non-null UserService instance")
    void createUserService_shouldReturnNonNull() {
        UserService userService = serviceConfig.createUserService();
        assertNotNull(userService, "UserService should not be null");
    }

    @Test
    @DisplayName("createRecordService should return a non-null RecordService instance")
    void createRecordService_shouldReturnNonNull() {
        RecordService recordService = serviceConfig.createRecordService();
        assertNotNull(recordService, "RecordService should not be null");
    }

    @Test
    @DisplayName("createCategoryService should return a non-null CategoryService instance")
    void createCategoryService_shouldReturnNonNull() {
        CategoryService categoryService = serviceConfig.createCategoryService();
        assertNotNull(categoryService, "CategoryService should not be null");
    }

    @Test
    @DisplayName("createStatisticsService should return a non-null StatisticsService instance")
    void createStatisticsService_shouldReturnNonNull() {
        StatisticsService statisticsService = serviceConfig.createStatisticsService();
        assertNotNull(statisticsService, "StatisticsService should not be null");
    }
}
