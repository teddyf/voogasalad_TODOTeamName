package boardObjects;

import editor.backend.Interaction;

/**
 * A board object with which the player character will have some active interaction (i.e. pressing 'A'
 * or stepping on a switch which elicits a response.
 *
 * @author Filip Mazurek
 */
public class CommunicableBoardObject extends AbstractBoardObject {

    public CommunicableBoardObject() {
        super();
        makeNotWalkable(); // tentative. What about notes on the floor, etc.
    }

}
