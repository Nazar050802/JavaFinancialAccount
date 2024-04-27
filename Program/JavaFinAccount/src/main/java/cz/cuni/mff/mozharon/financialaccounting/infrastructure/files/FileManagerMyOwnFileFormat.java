package cz.cuni.mff.mozharon.financialaccounting.infrastructure.files;

import cz.cuni.mff.mozharon.financialaccounting.application.config.ServiceConfig;
import cz.cuni.mff.mozharon.financialaccounting.application.services.CategoryService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.common.ServiceContainer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionIncorrectCredentials;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.exceptions.ExceptionParseRecordType;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryCategoryRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryUserRepository;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.CategoriesSerializer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.RecordSerializer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.UserSerializer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.FileUtility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileManagerMyOwnFileFormat implements FileManager {

    private final static String PATH_TO_STORE_SAVES = "saves/";

    @Override
    public ServiceContainer readDataFromFile(UserService userServiceWithCurrentCredentials) throws NoSuchAlgorithmException, IOException, InvalidUserException, InvalidCategoryException, InvalidAmountException, ExceptionParseRecordType, ExceptionIncorrectCredentials {
        String filePath = PATH_TO_STORE_SAVES + userServiceWithCurrentCredentials.getHashedSha1UserLoginName();

        ServiceContainer serviceContainer = getServiceContainer();

        if(!FileUtility.fileExists(filePath)){
            FileUtility.createNewFile(filePath);
        }
        else {
            int numberOfLinesInFile = FileUtility.countLines(filePath);

            for(int i = 1; i <= numberOfLinesInFile; i++){
                String line = FileUtility.getLineFromFile(filePath, i);
                if(line != null){
                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                        case "USER":
                            processUserDataLine(userServiceWithCurrentCredentials, line, serviceContainer);
                            break;
                        case "CATEGORY":
                            processCategoryDataLine(line, serviceContainer);
                            break;
                        case "RECORD":
                            processRecordDataLine(line, serviceContainer);
                            break;
                    }

                }
            }
        }

        return serviceContainer;
    }

    private static void processRecordDataLine(String line, ServiceContainer serviceContainer) throws InvalidAmountException, ExceptionParseRecordType, InvalidCategoryException {
        RecordSerializer recordSerializer = new RecordSerializer();
        Record record = recordSerializer.deserialize(line);
        serviceContainer.getRecordService().addRecord(record);
    }

    private static void processCategoryDataLine(String line, ServiceContainer serviceContainer) throws InvalidCategoryException {
        CategoriesSerializer categoriesSerializer = new CategoriesSerializer();
        Category category = categoriesSerializer.deserialize(line);
        serviceContainer.getCategoryService().addCategory(category);
    }

    private static void processUserDataLine(UserService userServiceWithCurrentCredentials, String line, ServiceContainer serviceContainer) throws InvalidUserException, NoSuchAlgorithmException, ExceptionIncorrectCredentials {
        UserSerializer userSerializer = new UserSerializer();
        User userFromFile = userSerializer.deserialize(line);
        serviceContainer.getUserService().setUser(userFromFile);

        if(!validateUserCredentials(userServiceWithCurrentCredentials.getUser(), serviceContainer.getUserService())){
            throw new ExceptionIncorrectCredentials("Wrong credentials");
        }

        serviceContainer.getUserService().getUser().setLoginName(userFromFile.getLoginName());
        serviceContainer.getUserService().getUser().setPassword(userFromFile.getPassword());
    }

    private static boolean validateUserCredentials(User currentUserCredentials, UserService userService) throws NoSuchAlgorithmException {
        return userService.checkCredentials(currentUserCredentials.getLoginName(), currentUserCredentials.getPassword());
    }

    private static ServiceContainer getServiceContainer() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        InMemoryCategoryRepository inMemoryCategoryRepository = new InMemoryCategoryRepository();
        InMemoryRecordRepository inMemoryRecordRepository = new InMemoryRecordRepository();

        ServiceConfig serviceConfig = new ServiceConfig(inMemoryUserRepository, inMemoryRecordRepository, inMemoryCategoryRepository);

        return new ServiceContainer(
                serviceConfig.createUserService(),
                serviceConfig.createRecordService(),
                serviceConfig.createCategoryService()
        );
    }

    @Override
    public void saveDataToFile(ServiceContainer serviceContainer) throws IOException, NoSuchAlgorithmException {
        FileUtility.ensureDirectoryExists(PATH_TO_STORE_SAVES);
        String filePath = PATH_TO_STORE_SAVES + serviceContainer.getUserService().getHashedSha1UserLoginName();

        if(!FileUtility.fileExists(filePath)){
            FileUtility.createNewFile(filePath);
        }

        // Firstly, write User Data to file
        saveUser(serviceContainer.getUserService(), filePath);

        // Then, write all Categories to file
        saveAllCategories(serviceContainer.getCategoryService(), filePath);

        // Then, write all Records to file
        saveAllRecords(serviceContainer.getRecordService(), filePath);
    }

    private static void saveUser(UserService userService, String filePath) throws IOException, NoSuchAlgorithmException {
        UserSerializer userSerializer = new UserSerializer();
        FileUtility.writeToFile(filePath, userSerializer.serialize(userService.getUser()));
    }

    private static void saveAllCategories(CategoryService categoryService, String filePath) throws IOException {
        CategoriesSerializer categoriesSerializer = new CategoriesSerializer();
        for(Category category: categoryService.getAllCategories()){
            FileUtility.writeToFile(filePath, categoriesSerializer.serialize(category));
        }
    }

    private static void saveAllRecords(RecordService recordService, String filePath) throws IOException {
        RecordSerializer recordSerializer = new RecordSerializer();
        for(Record record: recordService.getAllRecords()){
            FileUtility.writeToFile(filePath, recordSerializer.serialize(record));
        }
    }
}
