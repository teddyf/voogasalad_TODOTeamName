package block;

import interactions.Interaction;
import interactions.TeleportInteraction;

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
            unlink(receiver);
            myReceiveBlock = (TeleportReceiveBlock) receiver;
            addInteraction(new TeleportInteraction(receiver.getRow(), receiver.getCol()));
            return true;
        }
        return false;
    }

    public void unlink(Block receiver) {
        if (receiver.equals(myReceiveBlock)) {
            for(Interaction i : getInteractions()) { // if a new destination is set, ensure old one is erased
                if(i instanceof TeleportInteraction) {
                    removeInteraction(i);
                }
            }
            myReceiveBlock = null;
        }
    }
}
