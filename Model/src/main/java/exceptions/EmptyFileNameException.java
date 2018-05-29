package exceptions;

public class EmptyFileNameException extends Exception {

    public EmptyFileNameException(final String message) {
        super(message);
    }

    public EmptyFileNameException(final String message, final Throwable cause) {
        super(message, cause);
    }


}
