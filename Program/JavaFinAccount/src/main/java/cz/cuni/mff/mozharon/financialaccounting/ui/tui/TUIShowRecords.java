package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.formatters.RecordTypeFormatter;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.ShowRecordsController;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TUIShowRecords {
    private final ShowRecordsController showRecordsController;
    private static final Scanner scanner = new Scanner(System.in);

    private DateAndTime currentFilterDate;
    private String currentFilterCategory;

    public TUIShowRecords(ShowRecordsController showRecordsController) {
        this.showRecordsController = showRecordsController;
    }

    public void showMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("1. Show all records sorted by date");
            System.out.println("2. Show records by specific date");
            System.out.println("3. Show records by category");
            System.out.println("4. Return to Main Menu");
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
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            TUIClearConsole.clearConsole();
        }
    }

    private void requestDateFromUser() {
        System.out.print("Enter date (dd mm yyyy): ");
        String dateStr = scanner.nextLine().trim();
        dateStr += " 00 00 00";
        currentFilterDate = new DateAndTime(dateStr);
    }

    private void requestCategoryFromUser() {
        System.out.print("Enter category name: ");
        currentFilterCategory = scanner.nextLine();
    }

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

    private List<Record> fetchRecordsByType(int type, int currentPage) {
        TUIClearConsole.clearConsole();
        return switch (type) {
            case 1 -> showRecordsController.getSortedRecords(true, currentPage);
            case 2 -> showRecordsController.getRecordsByDate(currentFilterDate, currentPage);
            case 3 -> showRecordsController.getRecordsByCategory(currentFilterCategory, currentPage);
            default -> Collections.emptyList();
        };
    }

    private void printHeader(int currentPage) {
        String header = "+-----------------------------------+";
        System.out.println(header);
        System.out.printf("| Current Page: %-20d|\n", currentPage);
        System.out.println(header);
    }

    private void printRecordInfo(Record record){
        System.out.printf("| %s. %s: %s, %s, %s \n",

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

    private static String formatNumber(int number) {
        if (number >= 0 && number < 10) {
            return String.format("0%d", number);
        } else {
            return String.valueOf(number);
        }
    }
}
