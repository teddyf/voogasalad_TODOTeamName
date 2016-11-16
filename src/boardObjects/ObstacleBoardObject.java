package boardObjects;

import editor.backend.Interaction;

/**
 * A board object which in general should not allow the player character to step on or otherwise
 * interact with.
 *
 * @author Filip Mazurek
 */
public class ObstacleBoardObject extends AbstractBoardObject {

    public ObstacleBoardObject() {
        super();
        makeNotWalkable();
    }

}
