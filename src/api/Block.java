package api;

import block.BlockUpdate;
import interactions.Interaction;
import java.io.Serializable;
import java.util.List;

/**
 * The block interface, the general type of object which may be placed on the board (back end representation).
 *
 * @author Aninda Manocha, Filip Mazurek
 */

public interface Block extends Serializable {

    /**
     * Apply all the interactions in the block which are triggered by stepping on that block tile.
     *
     * @param player: the player object--in case of player modification (e.g. teleportation, etc.)
     * @return list of updates which need to be applied by the front end (e.g. re-rendering, etc.)
     */
    List<BlockUpdate> stepInteract(Player player);

    /**
     * Apply all the interactions in the block which are triggered by talking to that block tile.
     *
     * @param player: the player object--in case of player modification (e.g. teleportation, etc.)
     * @return list of updates which need to be applied by the front end (e.g. re-rendering, etc.)
     */
    List<BlockUpdate> talkInteract(Player player);

    /**
     * Make a link between this block and another selected block. Only applies if both blocks may be linked to each
     * other. If block has no such ability, default behavior is to return false.
     *
     * @param block: block to be linked to from this block
     * @param gridIndex: the grid on which the block resides (for multiple grid levels)
     * @return whether the link was successful
     */
    boolean link(Block block, int gridIndex);

    /**
     * Remove the link between the selected linked blocks. Fails if the blocks were not linked originally.
     *
     * @param block: block with which the link must be broken
     * @return whether the unlink was successful
     */
    boolean unlink(Block block);

    /**
     * Works to prepare a new image path name for new rendering. This is how blocks in the grid change how they look.
     *
     * @param name: the full original file path name
     * @param status: the new different status
     * @return the new file path
     */
    String replaceNameStatus(String name, String status);

    /**
     * Makes a copy of the block such that the block copy is indistinguishable from the original
     *
     * @return: copy of the original block
     */
    Block deepClone();

    /**
     * Add an interaction to the block which will be triggered upon stepping on the block.
     *
     * @param stepInteraction: an Interaction to add to the block
     * @return whether adding the interaction was successful
     */
    boolean addStepInteraction(Interaction stepInteraction);

    /**
     * Add an interaction to the block which will be triggered upon talking to the block.
     *
     * @param talkInteraction: an Interaction to add to the block
     * @return whether adding the interaction was successful
     */
    boolean addTalkInteraction(Interaction talkInteraction);

    /**
     * Remove a specific interaction instance from the list of stepInteractions
     *
     * @param stepInteraction: the specific interaction to remove
     * @return whether the interaction removal occurred
     */
    boolean removeStepInteraction(Interaction stepInteraction);

    /**
     * Remove a specific interaction instance from the list of talkInteractions
     *
     * @param talkInteraction: the specific interaction to remove
     * @return whether the interaction removal occurred
     */
    boolean removeTalkInteraction(Interaction talkInteraction);

    /* GETTERS */

    String getName();

    int getRow();

    int getCol();

    boolean isWalkable();

    List<Interaction> getStepInteractions();

    List<Interaction> getTalkInteractions();

    /* SETTERS */

    void setWalkableStatus(boolean status);
}
