package block;

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
    }

    public void openGate() {
        isOpen = OPEN;
        setWalkableStatus(true);
    }

    public void closeGate() {
        isOpen = CLOSED;
        setWalkableStatus(false);
    }

    /**
     * Changes whether the gate is open or closed. Difference is whether the player character may walk through the gate
     * and how it should be rendered.
     *
     * @return the rendering change for the front end
     */
    public BlockUpdate toggleOpenStatus() {
        String ext;
        if(this.isWalkable()) {
            setWalkableStatus(CLOSED);
            isOpen = CLOSED;
            ext = "CLOSED";
        }
        else {
            setWalkableStatus(OPEN);
            isOpen = OPEN;
            ext = "OPEN";
        }

        // notify front end to render the gate differently
        // TODO: refactor this ugly stuff following
        int extLoc = this.getName().lastIndexOf('_');
        String image = this.getName().substring(0, extLoc + 1) + ext;
        return new BlockUpdate(BlockUpdateType.RE_RENDER, getRow(), getCol(), image);
    }
}
