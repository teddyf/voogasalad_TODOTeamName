package boardObjects;

import editor.backend.IBlock;
import editor.backend.Interaction;
import grid.BlockType;

import java.util.ArrayList;
import java.util.List;

/**
 * The general type of object which may be placed on the board.
 *
 * @author Filip Mazurek
 */
public abstract class Block implements IBlock {

//    private BlockType myBlockType; // this might not really be needed
    private int myRow;
    private int myColumn;
    private String myName;
    private double myIdentifier;
    private boolean walkableStatus;
    private List<Interaction> myInteractions;

    public Block(String name) {
        myName = name;
    }

    private List<Interaction> getInteractions() {
        return new ArrayList<>(myInteractions);
    }

    public String getName() {
        return myName;
    }
    
    public int getRow() {
    	return myRow;
    }
    
    public int getCol() {
    	return myColumn;
    }

    protected boolean addInteraction(Interaction someInteraction) {
        return myInteractions.add(someInteraction);
    }

    protected void clearInteractions() {
        myInteractions.clear();
    }

    protected boolean removeInteraction(Interaction someInteraction) {
        return myInteractions.remove(someInteraction);
    }

    protected void setWalkableStatus(boolean status) {
        walkableStatus = status;
    }

    public boolean isWalkable() {
        return walkableStatus;
    }
}
