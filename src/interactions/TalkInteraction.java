package interactions;

/**
 * The class which handles all interactions which are instantiated by talking to the block.
 * @author Aninda Manocha, Filip Mazurek
 */

public interface TalkInteraction extends Interaction {
    void displayMessage(String message);
}

