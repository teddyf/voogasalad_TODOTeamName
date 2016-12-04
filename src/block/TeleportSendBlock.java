package block;

import interactions.Interaction;
import interactions.StepInteraction;
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
            addStepInteraction(new TeleportInteraction(receiver.getRow(), receiver.getCol()));
            return true;
        }
        return false;
    }

    public boolean unlink(Block receiver) {
        if (receiver.equals(myReceiveBlock)) {
            for(StepInteraction interaction : getStepInteractions()) { // if a new destination is set, ensure old one is erased
                if(interaction instanceof TeleportInteraction) {
                    removeStepInteraction(interaction);
                }
            }
            myReceiveBlock = null;
            return true;
        }
        return false;
    }
}
