package by.trjava.ivankharytanovich.util.generator;

public class IdGenerator {
    private static long longId;

    public static long getLongId() {
        return ++longId;
    }
}
