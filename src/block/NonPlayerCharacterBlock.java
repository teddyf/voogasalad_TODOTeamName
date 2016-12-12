package block;

/**
 * A board object with which the player character will talk in order to display a message to the user. The player
 * character contains multiple images and so will turn to face the user to talk to them.
 *
 * @author Filip Mazurek
 */
public class NonPlayerCharacterBlock extends CommunicatorBlock {

    public NonPlayerCharacterBlock(String name, int row, int col) {
        super(name, row, col);
    }

}
