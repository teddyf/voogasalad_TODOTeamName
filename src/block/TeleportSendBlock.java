package block;

/**
 * This send block sends the player to its corresponding receive block by changing the player position
 * @author Aninda Manocha, Filip Mazurek
 */

public abstract class TeleportSendBlock extends Block {
    private TeleportReceiveBlock myReceiveBlock;

    public TeleportSendBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }

    @Override
    public boolean link(Block receiver) {
        if(receiver instanceof TeleportReceiveBlock) {
            myReceiveBlock = (TeleportReceiveBlock) receiver;
            return true;
        }
        return false;
    }
}
