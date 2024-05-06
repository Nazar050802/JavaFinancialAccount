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

/**
 * Handles file operations using a custom format specific to this financial accounting application.
 */
public class FileManagerMyOwnFileFormat implements FileManager {

    private final static String PATH_TO_STORE_SAVES = "saves/";

    /**
     * Reads data from a file into the application, reconstructing the service state from the stored data.
     *
     * @param userServiceWithCurrentCredentials The UserService that includes the current user's credentials.
     * @return A ServiceContainer populated with the services configured from the file data.
     * @throws IOException If there is an issue reading from the file.
     * @throws NoSuchAlgorithmException If there is an issue with hashing during the process.
     * @throws InvalidUserException If user data is not valid.
     * @throws InvalidCategoryException If category data is not valid.
     * @throws InvalidAmountException If record data includes invalid amounts.
     * @throws ExceptionParseRecordType If there is a parsing error with record types.
     * @throws ExceptionIncorrectCredentials If the credentials do not match expected values.
     */
    @Override
    public ServiceContainer readDataFromFile(UserService userServiceWithCurrentCredentials) throws NoSuchAlgorithmException, IOException, InvalidUserException, InvalidCategoryException, InvalidAmountException, ExceptionParseRecordType, ExceptionIncorrectCredentials {
        String filePath = PATH_TO_STORE_SAVES + userServiceWithCurrentCredentials.getHashedSha1UserLoginName();

        ServiceContainer serviceContainer = getServiceContainer();

        if(!FileUtility.fileExists(filePath)){
            FileUtility.createNewFile(filePath, PATH_TO_STORE_SAVES);
            serviceContainer.getUserService().setUser(userServiceWithCurrentCredentials.getUser());
        }
        else {
            int numberOfLinesInFile = FileUtility.countLines(filePath);

            for(int i = 1; i <= numberOfLinesInFile; i++){
                String line = FileUtility.getLineFromFile(filePath, i);
                if(line != null){
                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                        case UserSerializer.keyWord:
                            processUserDataLine(userServiceWithCurrentCredentials, line, serviceContainer);
                            break;
                        case CategoriesSerializer.keyWord:
                            processCategoryDataLine(line, serviceContainer);
                            break;
                        case RecordSerializer.keyWord:
                            processRecordDataLine(line, serviceContainer);
                            break;
                    }

                }
            }
        }

        return serviceContainer;
    }

    /**
     * Processes a single line of record data and adds it to the service container.
     * @param line The serialized record line.
     * @param serviceContainer The container to store service data.
     * @throws InvalidAmountException If the amount is not valid.
     * @throws ExceptionParseRecordType If the record type cannot be parsed.
     * @throws InvalidCategoryException If the category is not valid.
     */
    private static void processRecordDataLine(String line, ServiceContainer serviceContainer) throws InvalidAmountException, ExceptionParseRecordType, InvalidCategoryException {
        RecordSerializer recordSerializer = new RecordSerializer();
        Record record = recordSerializer.deserialize(line);
        serviceContainer.getRecordService().addRecord(record);
    }

    /**
     * Processes a single line of category data and adds it to the service container.
     * @param line The serialized category line.
     * @param serviceContainer The container to store service data.
     * @throws InvalidCategoryException If the category is not valid.
     */
    private static void processCategoryDataLine(String line, ServiceContainer serviceContainer) throws InvalidCategoryException {
        CategoriesSerializer categoriesSerializer = new CategoriesSerializer();
        Category category = categoriesSerializer.deserialize(line);
        serviceContainer.getCategoryService().addCategory(category);
    }

    /**
     * Processes a single line of user data and adds it to the service container.
     * @param userServiceWithCurrentCredentials Service containing the current user's credentials.
     * @param line The serialized user line.
     * @param serviceContainer The container to store service data.
     * @throws InvalidUserException If the user data is not valid.
     * @throws NoSuchAlgorithmException If a hashing algorithm is required and not found.
     * @throws ExceptionIncorrectCredentials If the credentials do not match expected values.
     */
    private static void processUserDataLine(UserService userServiceWithCurrentCredentials, String line, ServiceContainer serviceContainer) throws InvalidUserException, NoSuchAlgorithmException, ExceptionIncorrectCredentials {
        UserSerializer userSerializer = new UserSerializer();
        User userFromFile = userSerializer.deserialize(line);
        serviceContainer.getUserService().setUser(userFromFile);

        if(!validateUserCredentials(userServiceWithCurrentCredentials.getUser(), serviceContainer.getUserService())){
            throw new ExceptionIncorrectCredentials("Wrong credentials");
        }

        serviceContainer.getUserService().setLoginName(userFromFile.getLoginName());
        serviceContainer.getUserService().setPassword(userFromFile.getPassword());
    }

    /**
     * Validates user credentials.
     * @param currentUserCredentials User holding current credentials for comparison.
     * @param userService Service containing the user's data.
     * @return true if credentials match, false otherwise.
     * @throws NoSuchAlgorithmException If a hashing algorithm is required and not found.
     */
    private static boolean validateUserCredentials(User currentUserCredentials, UserService userService) throws NoSuchAlgorithmException {
        return userService.checkCredentials(currentUserCredentials.getLoginName(), currentUserCredentials.getPassword());
    }

    /**
     * Retrieves a fully initialized ServiceContainer with required repositories and services.
     * @return A ServiceContainer ready to use.
     */
    private static ServiceContainer getServiceContainer() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        InMemoryCategoryRepository inMemoryCategoryRepository = new InMemoryCategoryRepository();
        InMemoryRecordRepository inMemoryRecordRepository = new InMemoryRecordRepository();

        ServiceConfig serviceConfig = new ServiceConfig(inMemoryUserRepository, inMemoryRecordRepository, inMemoryCategoryRepository);

        return new ServiceContainer(
                serviceConfig.createUserService(),
                serviceConfig.createRecordService(),
                serviceConfig.createCategoryService(),
                serviceConfig.createStatisticsService()
        );
    }

    /**
     * Writes the current application state to a file in a custom format.
     *
     * @param serviceContainer The container holding all services and their data.
     * @throws IOException If there is an issue writing to the file.
     * @throws NoSuchAlgorithmException If there is an issue with hashing during the process.
     */
    @Override
    public void saveDataToFile(ServiceContainer serviceContainer) throws IOException, NoSuchAlgorithmException {
        String filePath = PATH_TO_STORE_SAVES + serviceContainer.getUserService().getHashedSha1UserLoginName();

        if(!FileUtility.fileExists(filePath)){
            FileUtility.createNewFile(filePath, PATH_TO_STORE_SAVES);
        }
        else {
            FileUtility.clearFileContents(filePath);
        }

        // Firstly, write User Data to file
        saveUser(serviceContainer.getUserService(), filePath);

        // Then, write all Categories to file
        saveAllCategories(serviceContainer.getCategoryService(), filePath);

        // Then, write all Records to file
        saveAllRecords(serviceContainer.getRecordService(), filePath);
    }

    /**
     * Saves the user data to the specified file path.
     * This method serializes the user data and writes it to a file using {@link FileUtility}.
     *
     * @param userService The service containing the user data to save.
     * @param filePath The path of the file where the user data will be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     * @throws NoSuchAlgorithmException If there is an error during hashing in serialization.
     */
    private static void saveUser(UserService userService, String filePath) throws IOException, NoSuchAlgorithmException {
        UserSerializer userSerializer = new UserSerializer();
        FileUtility.writeToFile(filePath, userSerializer.serialize(userService.getUser()));
    }

    /**
     * Saves all categories from the CategoryService to the specified file path.
     * This method serializes each category and writes it to a file using {@link FileUtility}.
     *
     * @param categoryService The service containing the categories to save.
     * @param filePath The path of the file where the categories will be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    private static void saveAllCategories(CategoryService categoryService, String filePath) throws IOException {
        CategoriesSerializer categoriesSerializer = new CategoriesSerializer();
        for(Category category: categoryService.getAllCategories()){
            FileUtility.writeToFile(filePath, categoriesSerializer.serialize(category));
        }
    }

    /**
     * Saves all records from the RecordService to the specified file path.
     * This method serializes each record and writes it to a file using {@link FileUtility}.
     *
     * @param recordService The service containing the records to save.
     * @param filePath The path of the file where the records will be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    private static void saveAllRecords(RecordService recordService, String filePath) throws IOException {
        RecordSerializer recordSerializer = new RecordSerializer();
        for(Record record: recordService.getAllRecords()){
            FileUtility.writeToFile(filePath, recordSerializer.serialize(record));
        }
    }
}
