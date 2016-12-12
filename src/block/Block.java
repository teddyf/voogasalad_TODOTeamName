package block;

import api.IBlock;
import interactions.Interaction;
import player.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The general type of object which may be placed on the board (back end representation).
 *
 * @author Filip Mazurek, Daniel Chai, Aninda Manocha
 */

public abstract class Block implements IBlock {

    private String myName;
    private int myRow;
    private int myCol;
    private boolean isWalkable;
    private List<Interaction> myStepInteractions;
    private List<Interaction> myTalkInteractions;
    private String myMessage;

    public Block(String name,  int row, int col) {
        myName = name;
        myRow = row;
        myCol = col;
        isWalkable = false;
        myStepInteractions = new ArrayList<>();
        myTalkInteractions = new ArrayList<>();
    }

    /**
     * Apply all the interactions in the block which are triggered by stepping on that block tile.
     *
     * @param player: the player object--in case of player modification (e.g. teleportation, etc.)
     * @return list of updates which need to be applied by the front end (e.g. re-rendering, etc.)
     */
    public List<BlockUpdate> stepInteract(Player player) {
        List<BlockUpdate> blockUpdates = new ArrayList<>();
        for (Interaction interaction : myStepInteractions) {
            blockUpdates.addAll(interaction.act(player));
        }
        return blockUpdates;
    }

    /**
     * Apply all the interactions in the block which are triggered by talking to that block tile.
     *
     * @param player: the player object--in case of player modification (e.g. teleportation, etc.)
     * @return list of updates which need to be applied by the front end (e.g. re-rendering, etc.)
     */
    public List<BlockUpdate> talkInteract(Player player){
        List<BlockUpdate> blockUpdates = new ArrayList<>();
        if (myTalkInteractions.size() > 0) {
            for(Interaction interaction : myTalkInteractions) {
                blockUpdates.addAll(interaction.act(player));
            }
        }
        return blockUpdates;

    }

    /**
     * Make a link between this block and another selected block. Only applies if both blocks may be linked to each
     * other. If block has no such ability, default behavior is to return false.
     *
     * @param block: block to be linked to from this block
     * @param gridIndex: the grid on which the block resides (for multiple grid levels)
     * @return whether the link was successful
     */
    public boolean link(Block block, int gridIndex) {
        return false;
    }

    /**
     * Remove the link between the selected linked blocks. Fails if the blocks were not linked originally.
     *
     * @param block: block with which the link must be broken
     * @return whether the unlink was successful
     */
    public boolean unlink(Block block) {
        return false;
    }

    /*****GETTERS*****/

    public String getName() {
        return myName;
    }

    public int getRow() {
        return myRow;
    }

    public int getCol() {
        return myCol;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    //Interactions methods
    public List<Interaction> getStepInteractions() {
        return Collections.unmodifiableList(myStepInteractions);
    }

    public boolean addStepInteraction(Interaction stepInteraction) {
        return myStepInteractions.add(stepInteraction);
    }

    protected boolean removeStepInteraction(Interaction stepInteraction) {
        return myStepInteractions.remove(stepInteraction);
    }

    public List<Interaction> getTalkInteractions() {
        return Collections.unmodifiableList(myTalkInteractions);
    }

    public boolean addTalkInteraction(Interaction talkInteraction) {
        return myTalkInteractions.add(talkInteraction);
    }

    protected boolean removeTalkInteraction(Interaction talkInteraction) {
        return myTalkInteractions.remove(talkInteraction);
    }

    public String getMessage() {
        return myMessage;
    }

    /*****SETTERS******/
    public void setMessage(String message){
        this.myMessage = message;
    }
    public void setWalkableStatus(boolean status) {
        isWalkable = status;
    }
}
