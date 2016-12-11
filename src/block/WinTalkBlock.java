package block;

import interactions.WinInteraction;

/**
 * Block which allows the player to win by talking to it.
 *
 * @author Filip Mazurek
 */
public class WinTalkBlock extends WinBlock {

    public WinTalkBlock(String name,  int row, int col) {
        super(name, row, col);
        setWalkableStatus(false);
        addTalkInteraction(new WinInteraction());
    }
}
