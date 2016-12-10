package exceptions;

/**
 * Created by anindamanocha on 12/9/16.
 */

public class DeletePlayerWarning extends Warning {

    @Override
    public String getMessage() {
        return String.format(DELETE_PLAYER);
    }
}
