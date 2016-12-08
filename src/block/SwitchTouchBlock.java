package block;

import block.SwitchBlock;
import block.BlockType;

/**
 * A switch that is activated on pressing the main interaction button by the player character.
 *
 * @author Filip Mazurek
 */
public class SwitchTouchBlock extends SwitchBlock {

    public SwitchTouchBlock(String name, int row, int col) {
        super(name, row, col);
    }
}
