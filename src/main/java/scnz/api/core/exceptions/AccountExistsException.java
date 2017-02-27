package scnz.api.core.exceptions;

/**
 * Created by wanghe on 27/02/17.
 */
public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }
}
