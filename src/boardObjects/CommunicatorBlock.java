package boardObjects;

import grid.BlockType;

/**
 * A board object with which the player character will have some active interaction (i.e. pressing 'A'
 * or stepping on a switch which elicits a response.
 *
 * @author Filip Mazurek
 */
public class CommunicatorBlock extends Block {
    private static final BlockType myBlockType = BlockType.COMMUNICATOR;

    public CommunicatorBlock(String name) {
        super(name);
        makeNotWalkable(); // tentative. What about notes on the floor, etc.
    }

}
