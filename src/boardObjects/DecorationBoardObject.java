package boardObjects;

import editor.backend.Interaction;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek
 */
public class DecorationBoardObject extends AbstractBoardObject {

    public DecorationBoardObject() {
        super();
        makeWalkable();
    }



}
