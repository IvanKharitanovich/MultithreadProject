package by.trjava.ivankharytanovich.exception;

public class ResourceAccessException extends Exception{
    public ResourceAccessException() {
    }

    public ResourceAccessException(String message) {
        super(message);
    }

    public ResourceAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAccessException(Throwable cause) {
        super(cause);
    }
}
