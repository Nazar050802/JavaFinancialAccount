package infrastructureTest.utilsTest;

import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.FileUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilityTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Ensure directory exists or is created")
    void ensureDirectoryExists() {
        String directoryPath = tempDir.resolve("newDir").toString();
        assertTrue(FileUtility.ensureDirectoryExists(directoryPath));
        assertTrue(Files.exists(Path.of(directoryPath)));
    }

    @Test
    @DisplayName("Delete existing file")
    void deleteExistingFile() throws IOException {
        Path file = tempDir.resolve("testFile.txt");
        Files.createFile(file);

        assertTrue(FileUtility.deleteFile(file.toString()));
        assertFalse(Files.exists(file));
    }

    @Test
    @DisplayName("Return false when deleting non-existent file")
    void failToDeleteNonExistentFile() {
        String nonExistentFilePath = tempDir.resolve("nonExistentFile.txt").toString();
        assertFalse(FileUtility.deleteFile(nonExistentFilePath));
    }

    @Test
    @DisplayName("Check file existence")
    void checkFileExists() throws IOException {
        Path file = tempDir.resolve("testFile.txt");
        Files.createFile(file);

        assertTrue(FileUtility.fileExists(file.toString()));
    }

    @Test
    @DisplayName("Create new file successfully")
    void createNewFile() throws IOException {
        String filePath = tempDir.resolve("newFile.txt").toString();
        assertTrue(FileUtility.createNewFile(filePath, tempDir.toString()));
        assertTrue(Files.exists(Path.of(filePath)));
    }

    @Test
    @DisplayName("Write to file")
    void writeToFile() throws IOException {
        Path file = tempDir.resolve("testWrite.txt");
        Files.createFile(file);

        assertTrue(FileUtility.writeToFile(file.toString(), "Hello, world!"));
        assertEquals("Hello, world!", Files.readString(file).trim());
    }

    @Test
    @DisplayName("Count lines in file")
    void countLines() throws IOException {
        Path file = tempDir.resolve("testCountLines.txt");
        Files.writeString(file, "Line1\nLine2\nLine3");

        assertEquals(3, FileUtility.countLines(file.toString()));
    }

    @Test
    @DisplayName("Get specific line from file")
    void getLineFromFile() throws IOException {
        Path file = tempDir.resolve("testGetLine.txt");
        Files.writeString(file, "Line1\nLine2\nLine3");

        assertEquals("Line2", FileUtility.getLineFromFile(file.toString(), 2));
        assertNull(FileUtility.getLineFromFile(file.toString(), 4)); // Test line beyond file content
    }

    @Test
    @DisplayName("Clear contents of a file")
    void clearFileContents() throws IOException {
        Path file = tempDir.resolve("testClearContents.txt");
        Files.writeString(file, "Some content");

        FileUtility.clearFileContents(file.toString());
        assertEquals("", Files.readString(file));
    }
}
