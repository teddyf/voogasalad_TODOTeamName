package model.block.blocktypes;

import model.block.NPCDirection;
import model.interactions.NPCTurnToFaceInteraction;

/**
 * A board object with which the model.player character will talk in order to display a message to the user. The NPC contains
 * multiple images and so will turn to face the user to talk to them.
 *
 * @author Filip Mazurek
 */
public class NPCBlock extends CommunicatorBlock {
    private NPCDirection myDirection;

    public NPCBlock(String name, int row, int col) {
        super(name, row, col);
        myDirection = NPCDirection.DOWN;
        addTalkInteraction(new NPCTurnToFaceInteraction(this));
    }

    public void switchDirection(NPCDirection direction) {
        myDirection = direction;
    }
}
