package block;

/**
 * A board object which in general should not allow the player character to step on or otherwise
 * interact with.
 *
 * @author Filip Mazurek, Daniel Chai
 */
public class ObstacleBlock extends Block {

    public ObstacleBlock(String name, int row, int col) {
        super(name, row, col);
    }
}
