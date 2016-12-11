package block;

/**
 * Container class which lets observer know what to do to a changing block
 *
 * @author Filip Mazurek
 */

public class BlockUpdate {
    private BlockUpdateType myUpdateType;
    private int myRow;
    private int myColumn;

    public BlockUpdate(BlockUpdateType updateType, int row, int col) {
        myUpdateType = updateType;
        myRow = row;
        myColumn = col;
    }

    public int getRow() {
        return myRow;
    }

    public int getColumn() {
        return myColumn;
    }
}
