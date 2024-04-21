package cz.cuni.mff.mozharon.financialaccounting.infrastructure.files;

import java.io.File;

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

}
