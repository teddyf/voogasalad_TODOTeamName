package exceptions;

/**
 * Exception thrown when the user attempts to modify the grid to be impossibly small.
 *
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

