package block;

/**
 * Container class which lets observer know what to do to a changing block
 *
 * @author Filip Mazurek
 */
public class BlockUpdate {
    public BlockUpdateType myUpdateType;
    public int myRow;
    public int myColumn;

    public BlockUpdate(BlockUpdateType updateType, int row, int col) {
        myUpdateType = updateType;
        myRow = row;
        myColumn = col;
    }
}
