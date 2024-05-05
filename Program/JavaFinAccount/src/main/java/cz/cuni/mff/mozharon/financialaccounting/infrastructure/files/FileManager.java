package cz.cuni.mff.mozharon.financialaccounting.infrastructure.files;

import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;

/**
 * This interface ensures that implementing classes can read and write application data to and from files.
 */
public interface FileManager {

    /**
     * Reads data from a file and populates services within a service container.
     *
     * @param userService The user service which may contain data necessary for initialization or validation.
     * @return A fully populated ServiceContainer with the application's data loaded from a file.
     * @throws Exception If any other exception occurs during the operation.
     */
    ServiceContainer readDataFromFile(UserService userService) throws Exception;

    /**
     * Writes the current state of the application contained in a service container to a file.
     *
     * @param serviceContainer The service container holding all data to be saved.
     * @throws Exception If any other exception occurs during the operation.
     */
     void saveDataToFile(ServiceContainer serviceContainer) throws Exception;
}
