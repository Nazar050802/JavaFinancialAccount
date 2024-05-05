package cz.cuni.mff.mozharon.financialaccounting.ui.controllers;

import cz.cuni.mff.mozharon.financialaccounting.application.config.ServiceConfig;
import cz.cuni.mff.mozharon.financialaccounting.application.services.StatisticsService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.files.FileManagerMyOwnFileFormat;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryCategoryRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryUserRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ControllerCore {
    public ServiceContainer serviceContainer;
    private InMemoryUserRepository inMemoryUserRepository;
    private InMemoryCategoryRepository inMemoryCategoryRepository;
    private InMemoryRecordRepository inMemoryRecordRepository;

    public ControllerCore() {
        this.inMemoryUserRepository = new InMemoryUserRepository();
        this.inMemoryCategoryRepository = new InMemoryCategoryRepository();
        this.inMemoryRecordRepository = new InMemoryRecordRepository();

        setServiceContainer();
    }

    private void setServiceContainer() {
        ServiceConfig serviceConfig = new ServiceConfig(inMemoryUserRepository, inMemoryRecordRepository, inMemoryCategoryRepository);
        this.serviceContainer = new ServiceContainer(serviceConfig.createUserService(), serviceConfig.createRecordService(), serviceConfig.createCategoryService(), serviceConfig.createStatisticsService());
    }

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
