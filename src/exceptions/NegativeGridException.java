package exceptions;

/**
 * @author Filip Mazurek
 */
public class NegativeGridException extends Exception implements Alert {
        public NegativeGridException() {
        super();
    }

    @Override
    public String getMessage () {
        return String.format(NEGATIVE_GRID);
    }
}

