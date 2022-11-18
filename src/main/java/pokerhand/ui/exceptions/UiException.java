package pokerhand.ui.exceptions;

/**
 * UiException
 *
 * <p>This exception is thrown when an error occurs in the user interface It is used to wrap any
 * exception that occurs in the user interface
 *
 * <p>
 */
public class UiException extends RuntimeException {
    public UiException(String message) {
        super(message);
    }
}
