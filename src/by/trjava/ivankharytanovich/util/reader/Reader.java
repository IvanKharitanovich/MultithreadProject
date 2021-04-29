package by.trjava.ivankharytanovich.util.reader;

import by.trjava.ivankharytanovich.exception.DataException;

import java.util.List;

public interface Reader {
    List<String> read(String fileLocation) throws DataException;
}
