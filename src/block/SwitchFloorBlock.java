package block;

import block.SwitchBlock;
import block.BlockType;

/**
 * A switch that is activated by being stepped on by the player character.
 *
 * @author Filip Mazurek
 */
public class SwitchFloorBlock extends SwitchBlock implements Walkable {

    public SwitchFloorBlock(String name, int row, int col) {
        super(name, BlockType.SWITCH_FLOOR, row, col);
        setWalkableStatus(IS_WALKABLE);
    }
}
