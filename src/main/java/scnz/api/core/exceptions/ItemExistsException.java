package scnz.api.core.exceptions;

/**
 * Created by wanghe on 27/02/17.
 */
public class ItemExistsException extends RuntimeException {
    public ItemExistsException() {
    }

    public ItemExistsException(String message) {
        super(message);
    }

    public ItemExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemExistsException(Throwable cause) {
        super(cause);
    }
}
