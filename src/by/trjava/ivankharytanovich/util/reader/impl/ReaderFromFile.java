package by.trjava.ivankharytanovich.util.reader.impl;

import by.trjava.ivankharytanovich.exception.DataException;
import by.trjava.ivankharytanovich.util.reader.Reader;
import by.trjava.ivankharytanovich.util.validation.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromFile implements Reader {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public List<String> read(String fileLocation) throws DataException {
        List<String> list = new ArrayList<>();
        String defaultDirName = "data";
        Path path = FileSystems.getDefault().getPath(defaultDirName, fileLocation);
        try {
            if (DataValidation.isEmpty(path)) {
                logger.error("EMPTY FILE: " + path.toString());
                throw new DataException("EMPTY FILE");
            }
            list = Files.readAllLines(path);
        } catch (IOException e) {
            logger.error("FILE NOT FOUND");
            throw new DataException("FILE NOT FOUND");
        }
        return list;
    }
}
