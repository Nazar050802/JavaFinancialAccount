package cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils;

import java.io.*;

public class FileUtility {

    /**
     * Ensures that the specified directory exists.
     * @param directoryPath The path to the directory.
     * @return true if the directory exists or was successfully created.
     */
    public static boolean ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            return directory.mkdirs();  // Create the directory if it doesn't exist
        }
        return true;  // Return true if the directory already exists
    }

    /**
     * Deletes a file if it exists.
     * @param filePath The path to the file.
     * @return true if the file was successfully deleted.
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.delete();
    }

    /**
     * Checks if a file exists.
     * @param filePath The path to the file.
     * @return true if the file exists.
     */
    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean createNewFile(String filePath, String rootPath) throws IOException {
        ensureDirectoryExists(rootPath);
        File file = new File(filePath);
        return file.createNewFile();
    }

    public static boolean writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);

        if(fileExists(filePath)){
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(content);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw e;
            }
            return true; // return true if writing was successful
        }

        return false;
    }

    public static int countLines(String filePath) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    public static String getLineFromFile(String filePath, int lineNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == lineNumber) {
                    return line;
                }
            }
        }
        return null; // Return null if the line number is not found
    }

    public static void clearFileContents(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
        }
    }
}
