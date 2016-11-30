package block;

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
public abstract class Block extends Observable implements ShallowBlock {

    private BlockType myBlockType;
    private String myName;
    protected int myRow;
    protected int myCol;
    private boolean walkableStatus;
    private List<Interaction> myInteractions;
    private String myMessage;

    public Block(String name, BlockType blockType, int row, int col) {
        myBlockType = blockType;
        myName = name;
        myRow = row;
        myCol = col;
        myInteractions = new ArrayList<>();
    }

    public void interactWithPlayer() {
        for (Interaction i : myInteractions) {
            i.act();
        }
    }

    public void doMessage() {
        setChanged();
        notifyObservers(new BlockUpdateNotification(BlockUpdateType.DISPLAY_MESSAGE, myRow, myCol));
    }

    /*****GETTERS*****/

    public BlockType getBlockType() {
        return myBlockType;
    }

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

    //Interactions methodsanindo
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

    protected void setBlockType(BlockType blockType) {
        myBlockType = blockType;
    }

    protected void setWalkableStatus(boolean status) {
        walkableStatus = status;
    }
}
