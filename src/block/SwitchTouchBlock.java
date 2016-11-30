package block;

import block.SwitchBlock;
import block.BlockType;

/**
 * A switch that is activated on pressing the main interaction button by the player character.
 *
 * @author Filip Mazurek
 */
public class SwitchTouchBlock extends SwitchBlock implements NotWalkable {

    public SwitchTouchBlock(String name, BlockType block, int row, int col) {
        super(name, BlockType.SWITCH_TOUCH, row, col);
        setWalkableStatus(IS_WALKABLE);
    }
}
