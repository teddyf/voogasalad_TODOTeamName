package block;

/**
 * A board object which does not allow the player character to step on or otherwise interact with.
 *
 * @author Filip Mazurek
 */
public class ObstacleBlock extends AbstractBlock {

    public ObstacleBlock(String name, int row, int col) {
        super(name, row, col);
    }
}
