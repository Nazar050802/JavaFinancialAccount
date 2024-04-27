package cz.cuni.mff.mozharon.financialaccounting.infrastructure.files;

import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;

public interface FileManager {

    public ServiceContainer readDataFromFile(UserService userService) throws Exception;
    public void saveDataToFile(ServiceContainer serviceContainer) throws Exception;
}
