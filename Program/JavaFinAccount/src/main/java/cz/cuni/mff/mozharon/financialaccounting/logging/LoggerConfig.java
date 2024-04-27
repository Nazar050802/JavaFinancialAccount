package cz.cuni.mff.mozharon.financialaccounting.logging;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerConfig {

    private static final String pathToLogFiles = "logs/";
    private static FileHandler fileHandler;

    static {
        setupFileHandler();
    }

    private static void setupFileHandler() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH+mm+ss_dd-MM-yyyy");
            LocalDateTime dateAndTimeNow = LocalDateTime.now();

            // Specify the directory and file name for the log files
            String logFileName = pathToLogFiles + dtf.format(dateAndTimeNow) + ".log";

            // Ensure the directory exists (create if it doesn't)
            new java.io.File(pathToLogFiles).mkdirs();

            // Create a FileHandler that writes to a single file
            fileHandler = new FileHandler(logFileName, true);

            //fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize logger", e);
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.addHandler(fileHandler);
        // Prevents logging to the console:
        // logger.setUseParentHandlers(false);
        return logger;
    }
}
