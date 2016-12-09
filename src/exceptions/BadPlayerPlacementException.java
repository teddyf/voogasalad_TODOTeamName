package exceptions;

/**
 * @author Filip Mazurek
 */
public class BadPlayerPlacementException extends Exception implements Alert{
    private int myRow;
    private int myColumn;


    public BadPlayerPlacementException(int row, int col) {
        super();
        myRow = row;
        myColumn = col;
    }

    @Override
    public String getMessage () {
        return String.format(BAD_PLAYER_PLACEMENT, myRow, myColumn);
    }
}

