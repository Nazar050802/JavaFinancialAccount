package cz.cuni.mff.mozharon.financialaccounting.ui.tui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TUIHelloMessage {
    public static void main(String[] args) {
        String programName = "Financial Application";
        String authorName = "Nazar Mozharov";
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String formattedDate = currentDate.format(formatter);
        String message = String.format(" \nWelcome to %s" +
                "\nAuthor: %s" +
                "\nCurrent date: %s \n ",
                programName, authorName, formattedDate);

        printFramedMessage(message);
    }

    private static void printFramedMessage(String message) {
        String[] lines = message.split("\n");
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        String border = "+" + "-".repeat(maxLength + 4) + "+";

        System.out.println(border); // Top border
        for (String line : lines) {
            System.out.printf("|  %-" + maxLength + "s  |\n", line);
        }
        System.out.println(border); // Bottom border
    }
}
