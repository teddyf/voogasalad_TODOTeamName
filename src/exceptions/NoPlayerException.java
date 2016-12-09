package exceptions;

/**
 * @author Filip Mazurek
 */

public class NoPlayerException extends Exception implements Alert {

    public NoPlayerException() {
        super();

    }

    @Override
    public String getMessage () {
        return String.format(NO_PLAYER);
    }
}
