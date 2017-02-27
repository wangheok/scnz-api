package scnz.api.core.exceptions;

/**
 * Created by wanghe on 27/02/17.
 */
public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException() {
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
