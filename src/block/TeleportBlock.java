package block;

import interactions.Interaction;
import interactions.TeleportInteraction;

/**
 * This send block sends the player to its corresponding receive block by changing the player position.
 *
 * @author Aninda Manocha, Filip Mazurek
 */

public abstract class TeleportBlock extends Block {
    private TeleportBlock myReceiveBlock;

    public TeleportBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }

    /**
     * Link the block to another TeleportBlock.
     *
     * @param receiver: TeleportBlcok to which stepping on this block will send the player character
     * @param gridIndex: the grid on which the block resides (for multiple grid levels)
     * @return whether the linkage was successful
     */
    @Override
    public boolean link(Block receiver, int gridIndex) {
        if(receiver instanceof TeleportBlock) {
            unlink(receiver);
            myReceiveBlock = (TeleportBlock) receiver;
            addStepInteraction(new TeleportInteraction(receiver.getRow(), receiver.getCol(), gridIndex));
            return true;
        }
        return false;
    }

    /**
     * Unlink this block from the selected TeleportBlock.
     *
     * @param receiver: TeleportBlock to which this block is connected.
     * @return whether the unlink was successful.
     */
    @Override
    public boolean unlink(Block receiver) {
        if (receiver.equals(myReceiveBlock)) {
            for(Interaction interaction : getStepInteractions()) { // if a new destination is set, ensure old one is erased
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
