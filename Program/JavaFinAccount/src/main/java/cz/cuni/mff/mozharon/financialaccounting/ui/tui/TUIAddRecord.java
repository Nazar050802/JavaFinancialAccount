package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.AddRecordController;

import java.util.Scanner;

/**
 * Text User Interface for adding records to the financial accounting system.
 */
public class TUIAddRecord {
    private static Scanner scanner = new Scanner(System.in);

    private AddRecordController addRecordController;

    /**
     * Constructor for TUIAddRecord.
     *
     * @param addRecordController Controller for adding records.
     */
    public TUIAddRecord(AddRecordController addRecordController) {
        this.addRecordController = addRecordController;
    }

    /**
     * Displays the menu for adding records, handles user input and interactions.
     */
    public void printAddRecord() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Add Record to System       |");
        System.out.println("+-----------------------------------+");

        try {
            // Ask if multiple records or single record
            System.out.println("Choose record adding mode:");
            System.out.println("1. Multiple Records");
            System.out.println("2. Single Record");
            System.out.print("Enter your choice (1 or 2): ");
            int mode = Integer.parseInt(scanner.nextLine());
            TUIClearConsole.clearConsole();

            boolean isMultiple = mode == 1;

            // Ask how to input record data
            System.out.println("Choose input method:");
            System.out.println("1. In one line (Format: amount,description,date[dd mm yyyy hh mm],category,type)");
            System.out.println("2. With help messages");
            System.out.print("Enter your choice (1 or 2): ");
            int inputMethod = Integer.parseInt(scanner.nextLine());

            if (inputMethod == 2) {
                if (isMultiple) {
                    addMultipleRecords();
                } else {
                    TUIClearConsole.clearConsole();
                    addRecordWithPrompts();
                }
            } else {
                if (isMultiple) {
                    addMultipleRecordsOneLine();
                } else {
                    TUIClearConsole.clearConsole();
                    System.out.println("Please enter record data in the following format:");
                    System.out.println("amount,description,date[dd mm yyyy hh mm],category,type");
                    System.out.println("Enter record data in one line:");
                    addRecordOneLine(scanner.nextLine());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Please try again.");
        }
    }

    /**
     * Handles adding multiple records through prompts.
     */
    private void addMultipleRecords() throws InvalidCategoryException, InvalidAmountException {
        System.out.print("Enter number of records to add: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            TUIClearConsole.clearConsole();
            System.out.println("Adding record " + (i + 1) + ":");
            addRecordWithPrompts();
        }
    }

    /**
     * Handles adding multiple records from single line inputs.
     */
    private void addMultipleRecordsOneLine() {
        System.out.print("Enter number of records to add: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            TUIClearConsole.clearConsole();
            System.out.println("Please enter record data in the following format:");
            System.out.println("amount,description,date[dd mm yyyy hh mm],category,type");
            System.out.println("Enter record " + (i + 1) + " data in one line:");
            addRecordOneLine(scanner.nextLine());
        }
    }

    /**
     * Adds a single record from a single line input.
     *
     * @param recordData Data for the record in a single line.
     */
    private void addRecordOneLine(String recordData) {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                String[] parts = recordData.split(",");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Incorrect number of fields. Expected 5 fields. You entered: " + parts.length);
                }
                Double amount = Double.parseDouble(parts[0].trim());
                String description = parts[1].trim();
                String dateAndTime = validateAndReformatDateString(parts[2].trim());

                String categoryName = parts[3].trim();
                createCategoryObjectInMemoryIfNotExists(categoryName);

                String recordType = parts[4].trim();

                addAndSaveRecord(amount, description, dateAndTime, categoryName, recordType);

                isValidInput = true;
            } catch (Exception e) {
                System.out.println("Error parsing input: " + e.getMessage());
                System.out.println("Please try again.");
                recordData = scanner.nextLine();
            }
        }
    }

    /**
     * Adds a single record with help from prompts.
     */
    private void addRecordWithPrompts() throws InvalidCategoryException, InvalidAmountException {
        Double amount = getDoubleInput("Enter amount:");
        String description = getStringInput("Enter description:");
        String dateAndTime = validateAndReformatDateString(getStringInput("Enter date and time (dd mm yyyy hh mm):"));
        String categoryName = getCategoryFromUser();
        String type = getStringInput("Enter record type (Income or Expense):");
        String recordType = type.toUpperCase();

        addAndSaveRecord(amount, description, dateAndTime, categoryName, recordType);
    }

    /**
     * Validates and adds a record, then saves it.
     *
     * @param amount       The amount of the transaction.
     * @param description  A description for the record.
     * @param dateAndTime  The date and time of the transaction.
     * @param categoryName The category of the transaction.
     * @param recordType   The type of the transaction.
     */
    private void addAndSaveRecord(Double amount, String description, String dateAndTime, String categoryName, String recordType) throws InvalidAmountException, InvalidCategoryException {
        addRecordController.addRecord(amount, description, dateAndTime, categoryName, recordType);
        addRecordController.saveData();

        System.out.println("Record added with amount: " + amount + ", description: " + description + ", date: " + dateAndTime + ", category: " + categoryName + ", type: " + recordType);

        TUICore.makePause(TUICore.TIME_SHOW_NEW_RECORD);
        TUIClearConsole.clearConsole();
    }

    /**
     * Gets a category name from user input.
     *
     * @return The category name.
     */
    private String getCategoryFromUser() throws InvalidCategoryException {
        String input;
        do {
            System.out.print("Enter category name: ");
            input = scanner.nextLine().trim();
        } while (input.isEmpty());

        createCategoryObjectInMemoryIfNotExists(input);

        return input;
    }

    /**
     * Ensures that a category object exists in memory by checking its presence
     * and creating a new one if it does not exist.
     *
     * @param category The name of the category to check and potentially create.
     * @throws InvalidCategoryException If the creation of the category fails due to invalid data.
     */
    private void createCategoryObjectInMemoryIfNotExists(String category) throws InvalidCategoryException {
        if (!validateCategory(category)) {
            createNewCategory(category);
        }
    }

    /**
     * Validates the date string to be in the correct format.
     *
     * @param dateStr The date string to validate.
     * @return A reformatted valid date string.
     */
    private static String validateAndReformatDateString(String dateStr) {
        String[] parts = dateStr.split(" ");
        if (parts.length != 5 || !isValidDate(parts)) {
            throw new IllegalArgumentException("Date must be in the format 'dd mm yyyy hh mm' with valid numbers.");
        }

        // Reformat to "dd mm yyyy 00 mm hh"
        return String.format("%s %s %s 00 %s %s", parts[0], parts[1], parts[2], parts[4], parts[3]);
    }

    /**
     * Validates whether a category exists in the system.
     *
     * @param category The name of the category to validate.
     * @return true if the category exists, false otherwise.
     */
    private boolean validateCategory(String category) {
        return addRecordController.checkCategoryExists(category);
    }

    /**
     * Creates a new category and adds it to the system if it does not already exist.
     *
     * @param category The name of the category to create.
     * @throws InvalidCategoryException If the creation of the category fails.
     */
    private void createNewCategory(String category) throws InvalidCategoryException {
        addRecordController.addCategory(category);
    }

    /**
     * Validates the components of a date to ensure they form a valid date.
     * This method checks if the day, month, hour, and minute components are within valid ranges.
     *
     * @param parts An array containing date components [day, month, year, hour, minute].
     * @return true if the date components form a valid date, false if any component is out of the valid range.
     */
    private static boolean isValidDate(String[] parts) {
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            int hour = Integer.parseInt(parts[3]);
            int minute = Integer.parseInt(parts[4]);

            // Validate ranges (simple validation, not accounting for month/day specifics or leap years)
            if (day < 1 || day > 31 || month < 1 || month > 12 || hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                throw new IllegalArgumentException("One or more date parts are out of range.");
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Retrieves string input from user with a prompt.
     *
     * @param prompt The message to display to the user.
     * @return The user input as a string.
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input;
        while ((input = scanner.nextLine()).isEmpty()) {
            System.out.println("Input cannot be empty. Please try again:");
            System.out.print(prompt);
        }
        return input;
    }

    /**
     * Retrieves a double value input from user with a prompt.
     *
     * @param prompt The message to display to the user.
     * @return The user input as a double.
     */
    private static Double getDoubleInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        boolean flagCorrectDouble = false;
        double result = 0.0;

        while (input.isEmpty() || !flagCorrectDouble) {
            try {
                result = Double.parseDouble(input);
                if(result > 0){
                    flagCorrectDouble = true;
                }
            }
            catch (NumberFormatException ignored) {}

            if (!flagCorrectDouble) {
                System.out.print("Invalid input. Please enter a valid number:");
                input = scanner.nextLine();
            }
        }

        return result;
    }
}
