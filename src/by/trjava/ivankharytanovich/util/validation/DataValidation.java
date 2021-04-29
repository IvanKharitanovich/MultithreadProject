package by.trjava.ivankharytanovich.util.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataValidation {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isEmpty(Path path) {
        boolean status = false;
        try {
            if (Files.size(path) < 1) {
                status = true;
            }
        } catch (IOException e) {
            logger.error("FILE NOT FOUND" + path.toString());
            status = true;
        }
        return status;
    }

}
