package exceptions;

public class BacktrackingException extends Exception {
    public BacktrackingException(final String message) {
        super(message);
    }

    public BacktrackingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
