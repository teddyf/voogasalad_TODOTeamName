package boardObjects;

import grid.BlockType;

/**
 * A board object which in general should not allow the player character to step on or otherwise
 * interact with.
 *
 * @author Filip Mazurek, Daniel Chai
 */
public class ObstacleBlock extends Block implements NotWalkable {

    public ObstacleBlock(String name, int row, int col) {
        super(name, BlockType.OBSTACLE, row, col);
        setWalkableStatus(IS_WALKABLE);
    }
}
