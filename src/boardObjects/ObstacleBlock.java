package boardObjects;

import grid.BlockType;

/**
 * A board object which in general should not allow the player character to step on or otherwise
 * interact with.
 *
 * @author Filip Mazurek
 */
public class ObstacleBlock extends Block implements NotWalkable{
    private static final BlockType myBlockType= BlockType.OBSTACLE;

    public ObstacleBlock(String name) {
        super(name);
        setWalkableStatus(IS_WALKABLE);
    }

}
