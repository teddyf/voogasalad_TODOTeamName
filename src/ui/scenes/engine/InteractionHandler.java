package ui.scenes.engine;

import block.BlockUpdate;

/**
 * This class handles interactions on the front end.
 * @author Aninda Manocha, Filip Mazurek, Bill Xiong
 */

public class InteractionHandler {
    public InteractionHandler() {

    }

    public void displayMessage(String message) {

    }

    public void enterBattle() {

    }

    public void renderBlock() {

    }

    public void handleUpdate(BlockUpdate blockUpdate) {
        switch (blockUpdate.getType()) {
            case BATTLE:
                break;
            case DISPLAY_MESSAGE:
                displayMessage(blockUpdate.getContent());
                break;
            case RE_RENDER:
                break;
            default:
                break;
        }
    }
}
