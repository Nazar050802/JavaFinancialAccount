package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidCategoryException;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.AddRecordController;

import java.math.BigDecimal;
import java.util.Scanner;

public class TUIAddRecord {
    private static Scanner scanner = new Scanner(System.in);

    private AddRecordController addRecordController;

    public TUIAddRecord(AddRecordController addRecordController) {
        this.addRecordController = addRecordController;
    }

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

    private void addMultipleRecords() throws InvalidCategoryException, InvalidAmountException {
        System.out.print("Enter number of records to add: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            TUIClearConsole.clearConsole();
            System.out.println("Adding record " + (i + 1) + ":");
            addRecordWithPrompts();
        }
    }

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

    private void addRecordOneLine(String recordData) {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                String[] parts = recordData.split(",");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Incorrect number of fields. Expected 5 fields. You entered: " + parts.length);
                }
                BigDecimal amount = new BigDecimal(parts[0].trim());
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

    private void addRecordWithPrompts() throws InvalidCategoryException, InvalidAmountException {
        BigDecimal amount = getBigDecimalInput("Enter amount:");
        String description = getStringInput("Enter description:");
        String dateAndTime = validateAndReformatDateString(getStringInput("Enter date and time (dd mm yyyy hh mm):"));
        String categoryName = getCategoryFromUser();
        String type = getStringInput("Enter record type (Income or Expense):");
        String recordType = type.toUpperCase();

        addAndSaveRecord(amount, description, dateAndTime, categoryName, recordType);
    }

    private void addAndSaveRecord(BigDecimal amount, String description, String dateAndTime, String categoryName, String recordType) throws InvalidAmountException, InvalidCategoryException {
        addRecordController.addRecord(amount, description, dateAndTime, categoryName, recordType);
        addRecordController.saveData();

        System.out.println("Record added with amount: " + amount + ", description: " + description + ", date: " + dateAndTime + ", category: " + categoryName + ", type: " + recordType);

        TUICore.makePause(TUICore.TIME_SHOW_NEW_RECORD);
        TUIClearConsole.clearConsole();
    }

    private String getCategoryFromUser() throws InvalidCategoryException {
        String input;
        do {
            System.out.print("Enter category name: ");
            input = scanner.nextLine().trim();
        } while (input.isEmpty());

        createCategoryObjectInMemoryIfNotExists(input);

        return input;
    }

    private void createCategoryObjectInMemoryIfNotExists(String category) throws InvalidCategoryException {
        if (!validateCategory(category)) {
            createNewCategory(category);
        }
    }

    private static String validateAndReformatDateString(String dateStr) {
        String[] parts = dateStr.split(" ");
        if (parts.length != 5 || !isValidDate(parts)) {
            throw new IllegalArgumentException("Date must be in the format 'dd mm yyyy hh mm' with valid numbers.");
        }

        // Reformat to "dd mm yyyy 00 mm hh"
        return String.format("%s %s %s 00 %s %s", parts[0], parts[1], parts[2], parts[4], parts[3]);
    }

    private boolean validateCategory(String category) {
        return addRecordController.checkCategoryExists(category);
    }

    private void createNewCategory(String category) throws InvalidCategoryException {
        addRecordController.addCategory(category);
    }

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

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input;
        while ((input = scanner.nextLine()).isEmpty()) {
            System.out.println("Input cannot be empty. Please try again:");
            System.out.print(prompt);
        }
        return input;
    }

    private static BigDecimal getBigDecimalInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextBigDecimal()) {
            scanner.next();
            System.out.print("Invalid input. Please enter a valid number:");
        }
        BigDecimal result = scanner.nextBigDecimal();
        scanner.nextLine(); // consume newline
        return result;
    }
}
