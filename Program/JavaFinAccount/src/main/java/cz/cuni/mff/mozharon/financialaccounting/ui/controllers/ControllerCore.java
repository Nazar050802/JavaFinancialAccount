package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.application.config.ServiceConfig;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.files.FileManagerMyOwnFileFormat;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryCategoryRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryUserRepository;

/**
 * Core controller class that orchestrates operations across different controllers and manages service container.
 */
public class ControllerCore {
    public ServiceContainer serviceContainer;
    private InMemoryUserRepository inMemoryUserRepository;
    private InMemoryCategoryRepository inMemoryCategoryRepository;
    private InMemoryRecordRepository inMemoryRecordRepository;

    /**
     * Initializes a new instance of ControllerCore and configures the service container.
     */
    public ControllerCore() {
        this.inMemoryUserRepository = new InMemoryUserRepository();
        this.inMemoryCategoryRepository = new InMemoryCategoryRepository();
        this.inMemoryRecordRepository = new InMemoryRecordRepository();

        setServiceContainer();
    }

    /**
     * Initializes and configures the ServiceContainer using the current state of the repositories.
     */
    private void setServiceContainer() {
        ServiceConfig serviceConfig = new ServiceConfig(inMemoryUserRepository, inMemoryRecordRepository, inMemoryCategoryRepository);
        this.serviceContainer = new ServiceContainer(serviceConfig.createUserService(), serviceConfig.createRecordService(), serviceConfig.createCategoryService(), serviceConfig.createStatisticsService());
    }

    /**
     * Attempts to read data from file storage into the system.
     * @return true if the read operation was successful, false otherwise.
     */
    public boolean tryToReadDataFromFile(){
        FileManagerMyOwnFileFormat fileManagerMyOwnFileFormat = new FileManagerMyOwnFileFormat();

        // Refresh our data
        this.inMemoryCategoryRepository = new InMemoryCategoryRepository();
        this.inMemoryRecordRepository = new InMemoryRecordRepository();

        setServiceContainer();

        try {
            serviceContainer = fileManagerMyOwnFileFormat.readDataFromFile(serviceContainer.getUserService());
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Attempts to save data from the system into file storage.
     * @return true if the save operation was successful, false otherwise.
     */
    public boolean tryToSaveDataToFile() {
        FileManagerMyOwnFileFormat fileManagerMyOwnFileFormat = new FileManagerMyOwnFileFormat();

        try {
            fileManagerMyOwnFileFormat.saveDataToFile(serviceContainer);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
