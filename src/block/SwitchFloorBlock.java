package block;

import block.SwitchBlock;
import block.BlockType;

/**
 * A switch that is activated by being stepped on by the player character.
 *
 * @author Filip Mazurek
 */
public class SwitchFloorBlock extends SwitchBlock {

    public SwitchFloorBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }
}
