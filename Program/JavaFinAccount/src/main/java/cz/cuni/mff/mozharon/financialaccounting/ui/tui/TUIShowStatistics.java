package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import cz.cuni.mff.mozharon.financialaccounting.application.utils.StatisticsUtils;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;
import cz.cuni.mff.mozharon.financialaccounting.ui.controllers.ShowStatisticsController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Handles the presentation of statistical data in the text user interface.
 */
public class TUIShowStatistics {
    private ShowStatisticsController controller;
    private Scanner scanner = new Scanner(System.in);

    private final static int GRAPH_MAX_NUMBER_OF_HASHES = 15;

    /**
     * Constructs a TUIShowStatistics instance with the provided controller.
     *
     * @param controller The controller used to handle statistics functionality.
     */
    public TUIShowStatistics(ShowStatisticsController controller) {
        this.controller = controller;
    }

    /**
     * Displays options for different statistical data sets and handles user input to select them.
     *
     * @throws InvalidStatisticField if an invalid field is encountered while fetching statistics.
     */
    public void displayOptions() throws InvalidStatisticField {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            System.out.println("Select statistic to display:");
            System.out.println("1. Yearly Statistics");
            System.out.println("2. Monthly Statistics");
            System.out.println("3. Daily Statistics");
            System.out.println("4. Return to Main Menu");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    TUIClearConsole.clearConsole();
                    displayYearlyStatistics();
                    break;
                case 2:
                    TUIClearConsole.clearConsole();
                    displayMonthlyStatistics();
                    break;
                case 3:
                    TUIClearConsole.clearConsole();
                    displayDailyStatistics();
                    break;
                case 4:
                    backToMainMenu = true;
                    TUIClearConsole.clearConsole();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            if (choice >= 1 && choice <= 3) {
                backToMainMenu = postGraphMenu();
                TUIClearConsole.clearConsole();
            }
        }
    }

    /**
     * Handles the interaction for deciding whether to return to the main menu or continue viewing statistics.
     *
     * @return true if the user chooses to go back to the main menu, false otherwise.
     */
    private boolean postGraphMenu() {
        System.out.println("\nDo you want to go back to the statistics menu? (Y/N)");
        String response = scanner.nextLine().trim().toUpperCase();
        return !response.equals("Y");
    }

    /**
     * Displays yearly statistics and handles its visualization.
     *
     * @throws InvalidStatisticField If there is an issue retrieving the statistics.
     */
    private void displayYearlyStatistics() throws InvalidStatisticField {
        Map<Integer, StatisticField> stats = controller.getYearlyStatistics();

        if (!stats.isEmpty()) {
            System.out.println("Yearly Statistics:");

            System.out.println("\n+ Income:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractYearsIncome(stats), true),
                            "Year: ",
                            "  "
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n- Expense:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractYearsExpense(stats), true),
                            "Year: ",
                            "  "
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n/ Profitability:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractYearsProfit(stats), true),
                            "Year: ",
                            "  "
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    true
            );
        }
    }

    /**
     * Displays monthly statistics for a selected year and handles its visualization.
     *
     * @throws InvalidStatisticField If there is an issue retrieving the statistics.
     */
    private void displayMonthlyStatistics() throws InvalidStatisticField {

        Map<Integer, Map<Integer, StatisticField>> stats = controller.getMonthlyStatistics();

        System.out.print("Enter the year (yyyy): ");
        String input = getStringFromInput();
        int year = Integer.parseInt(input);

        TUIClearConsole.clearConsole();

        if (!stats.isEmpty()) {
            System.out.printf("Monthly statistics of this year: %d\n", year);

            System.out.println("\n+ Income:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractMonthlyIncome(stats, year), true),
                            "Month: ",
                            String.format(".%d  ", year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n- Expense:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractMonthlyExpense(stats, year), true),
                            "Month: ",
                            String.format(".%d  ", year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n/ Profitability:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractMonthlyProfit(stats, year), true),
                            "Month: ",
                            String.format(".%d  ", year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    true
            );
        }
    }

    /**
     * Displays daily statistics for a selected month and year, handling its visualization.
     *
     * @throws InvalidStatisticField If there is an issue retrieving the statistics.
     */
    private void displayDailyStatistics() throws InvalidStatisticField {

        Map<Integer, Map<Integer, Map<Integer, StatisticField>>> stats = controller.getDailyStatistics();

        System.out.print("Enter the year (yyyy): ");
        String inputYear = getStringFromInput();
        int year = Integer.parseInt(inputYear);

        System.out.print("Enter the month (mm): ");
        String inputMonth = getStringFromInput();
        int month = Integer.parseInt(inputMonth);

        TUIClearConsole.clearConsole();

        if (!stats.isEmpty()) {
            System.out.printf("Daily statistics of this month: %d.%d\n", month, year);

            System.out.println("\n+ Income:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractDailyIncome(stats, year, month), true),
                            "Day: ",
                            String.format(".%d.%d  ", month, year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n- Expense:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractDailyExpense(stats, year, month), true),
                            "Day: ",
                            String.format(".%d.%d  ", month, year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    false
            );

            System.out.println("\n/ Profitability:");
            printGraph(
                    modifyMapKeysWithAdditionalText(
                            StatisticsUtils.sortKeysAndConvertToString(StatisticsUtils.extractDailyProfit(stats, year, month), true),
                            "Day: ",
                            String.format(".%d.%d  ", month, year)
                    ),

                    GRAPH_MAX_NUMBER_OF_HASHES,

                    true
            );
        }
    }

    /**
     * Retrieves a non-empty string from input, prompting repeatedly if the input is empty.
     *
     * @return A non-empty string input from the user.
     */
    private String getStringFromInput() {
        String input;
        while((input = scanner.nextLine()).isEmpty()){
            System.out.println("Incorrect input. Please try again: ");
        }
        return input;
    }

    /**
     * Modifies the keys of the provided map by surrounding each key with additional text.
     * @param originalMap The original map with String keys and Double values.
     * @param textInTheStart The text to be added to the start of each key.
     * @param textInTheEnd The text to be added to the end of each key.
     * @return A new map with modified keys and the same values.
     */
    public static Map<String, Double> modifyMapKeysWithAdditionalText(Map<String, Double> originalMap, String textInTheStart, String textInTheEnd) {
        return originalMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> textInTheStart + entry.getKey() + textInTheEnd,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    /**
     * Prints a graphical representation of statistics data.
     *
     * @param fieldsToPrintWithValues A map containing data to be printed in graph form.
     * @param maxLength Maximum length of the graph bar.
     * @param showAdditionalLegend Whether to show legend explaining the graph.
     */
    public static void printGraph(Map<String, Double> fieldsToPrintWithValues, int maxLength, boolean showAdditionalLegend) {

        double maxValue = fieldsToPrintWithValues.values().stream()
                .mapToDouble(Math::abs)
                .max()
                .orElse(0);

        int maxFieldNameLength = fieldsToPrintWithValues.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        double unitValue = maxValue / (double) maxLength;

        BiFunction<Integer, Integer, String> printDelimiter = (fieldLength, hashValue) ->
                "-".repeat(fieldLength + String.valueOf(hashValue).length() + maxLength * 2 + 2);

        System.out.println(printDelimiter.apply(maxFieldNameLength, (int)unitValue));

        for (Map.Entry<String, Double> entry : fieldsToPrintWithValues.entrySet()) {
            String field = entry.getKey();
            Double value = entry.getValue();

            String paddedGraphSymbols = getPaddedGraphSymbols(maxLength, value, unitValue);

            String line = String.format("%-" + maxFieldNameLength + "s %" + maxLength + "s|%-" + maxLength + "s",
                    field, (value < 0 ? paddedGraphSymbols : ""), (value >= 0 ? paddedGraphSymbols : ""));

            System.out.println(line);
        }

        System.out.println(printDelimiter.apply(maxFieldNameLength, (int)unitValue));

        System.out.println("Legend:");
        System.out.printf("  # = %.2f\n", unitValue);
        System.out.printf("  * is more than 0 but less than %.2f\n", unitValue);

        if(showAdditionalLegend){
            System.out.println("\n |# -> income");
            System.out.println(" | -> 0 value");
            System.out.println("#| -> expense");
        }

        System.out.println(printDelimiter.apply(maxFieldNameLength, (int)unitValue));

    }

    /**
     * Generates a padded string of graph symbols based on the value and unit value provided.
     * The graph visually represents data values as a series of hash marks.
     * If the value is small but non-zero, it is represented by an asterisk.
     *
     * @param maxLength The maximum length of the graph symbols.
     * @param value The value to represent graphically.
     * @param unitValue The unit value each graph symbol (hash mark) represents.
     * @return A string of graph symbols padded to the specified length.
     */
    private static String getPaddedGraphSymbols(int maxLength, Double value, double unitValue) {
        String graphSymbols;
        if (value != 0) {
            int numHashes = (int) (Math.abs(value) / unitValue);
            double remainder = (Math.abs(value) % unitValue) / unitValue;

            if (remainder > 0 && numHashes < maxLength) {
                if (value > 0) {
                    graphSymbols = "#".repeat(numHashes) + "*";
                } else {
                    graphSymbols = "*" + "#".repeat(numHashes);
                }
            } else {
                graphSymbols = "#".repeat(Math.max(numHashes, 1));
            }
        } else {
            graphSymbols = "";
        }

        graphSymbols = graphSymbols.substring(0, Math.min(graphSymbols.length(), maxLength));

        return (value >= 0)
                ? String.format("%-" + maxLength + "s", graphSymbols)
                : String.format("%" + maxLength + "s", graphSymbols);
    }
}
