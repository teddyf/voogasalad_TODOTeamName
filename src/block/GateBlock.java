package block;

import block.BlockType;

/**
 * A special type of block which may be connected to a switch. On player interaction with a switch, the gate will change
 * states between open and closed.
 *
 * @author Filip Mazurek
 */
public class GateBlock extends Block {
    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;
    private boolean isOpen;

    public GateBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(CLOSED);
        isOpen = CLOSED;
        // TODO: Should I place walkable status here? Or do with a separate method call? Maybe a way to clean this up
    }

    /* Changes whether the gate is open or closed. Difference in how it looks and whether the player can walk through it
     */
    public void toggleOpenStatus() {
        if(this.isWalkable()) {
            setWalkableStatus(CLOSED);
            isOpen = CLOSED;
        }
        else {
            setWalkableStatus(OPEN);
            isOpen = OPEN;
        }
        // notify front end to render the gate differently
        setChanged();
        notifyObservers(new BlockUpdateNotification(BlockUpdateType.RE_RENDER, getRow(), getCol()));
    }
}
