package block;

/**
 * A simplified representation of a block. This is all that the front end needs to successfully display a block,
 * and so no more information or control is given.
 *
 * @author Filip Mazurek
 */
public class ShallowBlock {
    private int myRow;
    private int myColumn;
    private String myName;

    public ShallowBlock(int row, int col, String name) {
        myRow = row;
        myColumn = col;
        myName = name;
    }

    public int getRow() {
        return myRow;
    }

    public int getColumn() {
        return myColumn;
    }

    public String getName() {
        return myName;
    }
}
