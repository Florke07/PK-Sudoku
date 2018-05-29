package exceptions;

public class WrongValueException extends Exception {

    public WrongValueException(final String message) {
        super(message);
    }

    public WrongValueException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
