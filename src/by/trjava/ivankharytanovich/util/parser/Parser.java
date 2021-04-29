package by.trjava.ivankharytanovich.util.parser;

import by.trjava.ivankharytanovich.exception.DataException;

public interface Parser<T> {
    T parse(String data) throws DataException;
}
