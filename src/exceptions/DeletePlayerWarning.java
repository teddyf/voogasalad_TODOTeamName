package exceptions;

/**
 * Created by anindamanocha on 12/9/16.
 */

public class DeletePlayerWarning extends Exception implements Alert {

    public DeletePlayerWarning() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format(DELETE_PLAYER);
    }
}
