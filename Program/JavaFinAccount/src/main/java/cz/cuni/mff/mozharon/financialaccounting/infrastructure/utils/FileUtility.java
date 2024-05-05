package cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils;

import java.io.*;

/**
 * Utility class providing static methods for file handling operations.
 */
public class FileUtility {

    /**
     * Ensures that the specified directory exists.
     * @param directoryPath The path to the directory.
     * @return true if the directory exists or was successfully created.
     */
    public static boolean ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            return directory.mkdirs();
        }
        return true;
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

    /**
     * Creates a new file at the specified path, ensuring the directory exists.
     * @param filePath The full path to the file to create.
     * @param rootPath The root directory to ensure exists.
     * @return true if the file was successfully created.
     * @throws IOException if an I/O error occurs.
     */
    public static boolean createNewFile(String filePath, String rootPath) throws IOException {
        ensureDirectoryExists(rootPath);
        File file = new File(filePath);
        return file.createNewFile();
    }

    /**
     * Writes content to a file at the specified path.
     * @param filePath The path to the file.
     * @param content The content to write.
     * @return true if the write operation was successful.
     * @throws IOException if an I/O error occurs during writing.
     */
    public static boolean writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);

        if(fileExists(filePath)){
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(content);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw e;
            }
            return true;
        }

        return false;
    }

    /**
     * Counts the number of lines in a file.
     * @param filePath The path to the file.
     * @return The number of lines in the file.
     * @throws IOException if an I/O error occurs during reading.
     */
    public static int countLines(String filePath) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    /**
     * Retrieves a specific line from a file by line number.
     * @param filePath The path to the file.
     * @param lineNumber The line number to retrieve.
     * @return The content of the specified line or null if the line does not exist.
     * @throws IOException if an I/O error occurs during reading.
     */
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
        return null;
    }

    /**
     * Clears the contents of a file without deleting the file.
     * @param filePath The path to the file.
     * @throws IOException if an I/O error occurs during writing.
     */
    public static void clearFileContents(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {}
    }
}
