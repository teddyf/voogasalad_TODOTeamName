package exceptions;

/**
 * Exception thrown in the editor when the user attempts to place their player character in a block which the player
 * should not be able to walk on.
 *
 * @author Filip Mazurek
 */
public class BadPlayerPlacementException extends Exception implements Alert {
    private int myRow;
    private int myColumn;


    public BadPlayerPlacementException(int row, int col) {
        super();
        myRow = row;
        myColumn = col;
    }

    @Override
    public String getMessage () {
        return String.format(BAD_PLAYER_PLACEMENT, Integer.toString(myRow), Integer.toString(myColumn));
    }
}

