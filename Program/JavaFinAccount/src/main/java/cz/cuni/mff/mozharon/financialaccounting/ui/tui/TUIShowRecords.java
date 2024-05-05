package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.RecordTypeFormatter;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.ShowRecordsController;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Text-based user interface component responsible for displaying records.
 */
public class TUIShowRecords {
    private final ShowRecordsController showRecordsController;
    private static final Scanner scanner = new Scanner(System.in);

    private DateAndTime currentFilterDate;
    private String currentFilterCategory;

    /**
     * Constructs a TUIShowRecords instance with a specified controller.
     *
     * @param showRecordsController The controller used to interact with record data.
     */
    public TUIShowRecords(ShowRecordsController showRecordsController) {
        this.showRecordsController = showRecordsController;
    }

    /**
     * Displays the main menu for record-related operations and handles user input to navigate various actions.
     */
    public void showMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("+-----------------------------------+");
            System.out.println("|            Show Records           |");
            System.out.println("+-----------------------------------+");
            System.out.println("1. Show all records sorted by date");
            System.out.println("2. Show records by specific date");
            System.out.println("3. Show records by category");
            System.out.println("4. Delete a record by ID");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    paginateRecords(1);
                    break;
                case 2:
                    requestDateFromUser();
                    paginateRecords(2);
                    break;
                case 3:
                    requestCategoryFromUser();
                    paginateRecords(3);
                    break;
                case 4:
                    TUIClearConsole.clearConsole();
                    deleteRecordById();
                    break;
                case 5:
                    back = true;
                    TUIClearConsole.clearConsole();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    /**
     * Deletes a record by its unique identifier.
     */
    private void deleteRecordById() {
        System.out.print("Enter the ID of the record to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        boolean deleted = showRecordsController.deleteRecordById(id);
        if (deleted) {
            System.out.println("Record deleted successfully.");
        } else {
            System.out.println("Record not found.");
        }

        TUICore.makePause(TUICore.TIME_SHOW_RECORD_DELETED);
        TUIClearConsole.clearConsole();
    }

    /**
     * Requests a date from the user for filtering records.
     */
    private void requestDateFromUser() {
        TUIClearConsole.clearConsole();
        System.out.print("Enter date (dd mm yyyy): ");
        String dateStr = scanner.nextLine().trim();
        dateStr += " 00 00 00";
        currentFilterDate = new DateAndTime(dateStr);
    }

    /**
     * Requests a category name from the user for filtering records.
     */
    private void requestCategoryFromUser() {
        TUIClearConsole.clearConsole();
        System.out.print("Enter category name: ");
        currentFilterCategory = scanner.nextLine();
    }

    /**
     * Manages pagination for displaying records and handles user navigation through pages.
     *
     * @param type The type of record listing requested (all, by date, by category).
     */
    private void paginateRecords(int type) {
        int currentPage = 1;
        List<Record> records;
        boolean exit = false;

        while (!exit) {
            records = fetchRecordsByType(type, currentPage);
            printHeader(currentPage);

            if (records.isEmpty() && currentPage > 1) {
                System.out.println("No more records to show.");
                currentPage--;
            } else {
                records.forEach(this::printRecordInfo);
            }

            System.out.println("\n[n]ext page, [p]revious page, [b]ack. Input n or p or b.");
            String command = scanner.nextLine();
            if ("n".equals(command)) {
                currentPage++;
            } else if ("p".equals(command) && currentPage > 1) {
                currentPage--;
            } else if ("b".equals(command)) {
                exit = true;
            }
        }
        TUIClearConsole.clearConsole();
    }

    /**
     * Fetches records based on the specified type and page number.
     *
     * @param type The type of records to fetch.
     * @param currentPage The current page number.
     * @return A list of records for the specified page.
     */
    private List<Record> fetchRecordsByType(int type, int currentPage) {
        TUIClearConsole.clearConsole();
        return switch (type) {
            case 1 -> showRecordsController.getSortedRecords(true, currentPage);
            case 2 -> showRecordsController.getRecordsByDate(currentFilterDate, currentPage);
            case 3 -> showRecordsController.getRecordsByCategory(currentFilterCategory, currentPage);
            default -> Collections.emptyList();
        };
    }

    /**
     * Prints the header for the record display, indicating the current page number.
     *
     * @param currentPage The current page being displayed.
     */
    private void printHeader(int currentPage) {
        String header = "+-----------------------------------+";
        System.out.println(header);
        System.out.printf("| Current Page: %-20d|\n", currentPage);
        System.out.println(header);
    }

    /**
     * Prints detailed information for a single record.
     *
     * @param record The record to print information for.
     */
    private void printRecordInfo(Record record){
        System.out.printf("| %s | %s. %s: %s, %s, %s \n",

                record.getId(),

                String.format("%s-%s-%s %s:%s",
                        formatNumber(record.getDateAndTime().getDay()),
                        formatNumber(record.getDateAndTime().getMonth()),
                        record.getDateAndTime().getYear(),
                        formatNumber(record.getDateAndTime().getHours()),
                        formatNumber(record.getDateAndTime().getMinutes())
                ),

                RecordTypeFormatter.formatForExternalUse(record.getRecordType()),
                record.getAmount().toString(),
                record.getCategory().getName(),
                record.getDescription());

    }

    /**
     * Formats a number with leading zero if it is less than 10.
     *
     * @param number The number to format.
     * @return A formatted string with a leading zero if necessary.
     */
    private static String formatNumber(int number) {
        if (number >= 0 && number < 10) {
            return String.format("0%d", number);
        } else {
            return String.valueOf(number);
        }
    }
}
