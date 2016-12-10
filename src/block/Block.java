package block;

import api.IBlock;
import interactions.Interaction;
import interactions.StepInteraction;
import interactions.TalkInteraction;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The general type of object which may be placed on the board.
 *
 * @author Filip Mazurek, Daniel Chai, Aninda Manocha
 */

public abstract class Block extends Observable implements IBlock {

    private String myName;
    private int myRow;
    private int myCol;
    private boolean isWalkable;
    private List<StepInteraction> myStepInteractions;
    private List<TalkInteraction> myTalkInteractions;
    private List<BlockUpdate> blockUpdates;
    private String myMessage;

    public Block(String name,  int row, int col) {
        myName = name;
        myRow = row;
        myCol = col;
        isWalkable = false;
        myStepInteractions = new ArrayList<>();
        myTalkInteractions = new ArrayList<>();
        blockUpdates = new ArrayList<>();
    }

    public boolean stepInteract(Player player) {
        for (Interaction interaction : myStepInteractions) {
            blockUpdates.addAll(interaction.act(player));
        }
        return (myStepInteractions.size() > 0);
    }
    public List<BlockUpdate> talkInteract(Player player, String message){
        List<BlockUpdate> blockUpdates = new ArrayList<>();
        for(TalkInteraction interaction : getTalkInteractions()) {
           blockUpdates.addAll(interaction.act(player));
        }
        for(TalkInteraction interaction : getTalkInteractions()) {
            interaction.displayMessage(message);
            doMessage();
        }
        return blockUpdates;
    }


    public void doMessage() {
        setChanged();
        notifyObservers(new BlockUpdate(BlockUpdateType.DISPLAY_MESSAGE, myRow, myCol));
    }

    public boolean link(Block block, int gridIndex) {
        return false;
    }

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
    public List<StepInteraction> getStepInteractions() {
        return Collections.unmodifiableList(myStepInteractions);
    }

    public boolean addStepInteraction(StepInteraction stepInteraction) {
        return myStepInteractions.add(stepInteraction);
    }

    protected boolean removeStepInteraction(StepInteraction stepInteraction) {
        return myStepInteractions.remove(stepInteraction);
    }

    public List<TalkInteraction> getTalkInteractions() {
        return Collections.unmodifiableList(myTalkInteractions);
    }

    public boolean addTalkInteraction(TalkInteraction talkInteraction) {
        return myTalkInteractions.add(talkInteraction);
    }

    protected boolean removeTalkInteraction(TalkInteraction talkInteraction) {
        return myTalkInteractions.remove(talkInteraction);
    }

    public List<BlockUpdate> getBlockUpdates() {
        return blockUpdates;
    }

    public String getMessage() {
        return myMessage;
    }

    /*****SETTERS******/
    public void setMessage(String message){
        this.myMessage = message;
    }
    protected void setWalkableStatus(boolean status) {
        isWalkable = status;
    }
}
