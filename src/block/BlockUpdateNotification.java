package block;

/**
 * Container class which lets observer know what to do to a changing block
 *
 * @author Filip Mazurek
 */
public class BlockUpdateNotification {
    public BlockUpdateType myUpdateType;
    public int myRow;
    public int myColumn;

    public BlockUpdateNotification(BlockUpdateType updateType, int row, int col) {
        myUpdateType = updateType;
        myRow = row;
        myColumn = col;
    }
}
