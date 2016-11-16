package boardObjects;

import editor.backend.Interaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The general type of object which may be placed on the board.
 *
 * @author Filip Mazurek
 */
public abstract class AbstractBoardObject {
    private static final boolean WALK_STATUS_TRUE = true;
    private static final boolean WALK_STATUS_FALSE = false;

    private String myName;
    private double myIdentifier;
    private boolean isWalkable;

    private List<Interaction> myInteractions;

    private List<Interaction> getInteractions() {
        return new ArrayList<>(myInteractions);
    }

    public String getName() {
        return myName;
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

    protected void makeWalkable() {
        isWalkable = WALK_STATUS_TRUE;
    }

    protected void makeNotWalkable() {
        isWalkable = WALK_STATUS_FALSE;
    }
}
