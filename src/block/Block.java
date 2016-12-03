package block;

import api.IBlock;
import interactions.Interaction;
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
    private boolean walkableStatus;
    private List<Interaction> myInteractions;
    private String myMessage;

    public Block(String name,  int row, int col) {
        myName = name;
        myRow = row;
        myCol = col;
        myInteractions = new ArrayList<>();
    }

    public void stepInteract() {
        for (Interaction i : myInteractions) {
            i.actOnStep();
        }
    }
    public void talkInteract(String message){
        for(Interaction i : myInteractions){
            i.actOnTalk(message);
        }
    }

    public void doMessage() {
        setChanged();
        notifyObservers(new BlockUpdateNotification(BlockUpdateType.DISPLAY_MESSAGE, myRow, myCol));
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
        return walkableStatus;
    }

    //Interactions methods
    public List<Interaction> getInteractions() {
        return Collections.unmodifiableList(myInteractions);
    }

    public boolean addInteraction(Interaction someInteraction) {
        return myInteractions.add(someInteraction);
    }

    protected void clearInteractions() {
        myInteractions.clear();
    }

    protected boolean removeInteraction(Interaction someInteraction) {
        return myInteractions.remove(someInteraction);
    }

    /*****SETTERS******/

    protected void setWalkableStatus(boolean status) {
        walkableStatus = status;
    }
}
