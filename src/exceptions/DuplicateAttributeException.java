package exceptions;

/**
 * Created by anindamanocha on 12/10/16.
 */

public class DuplicateAttributeException extends Exception implements Alert {
    public DuplicateAttributeException() {
        super();

    }

    @Override
    public String getMessage () {
        return String.format(DUPLICATE_PLAYER_ATTRIBUTE);
    }
}
