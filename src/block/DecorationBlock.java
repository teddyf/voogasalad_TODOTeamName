package block;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek, Aninda Manocha
 */
public class DecorationBlock extends AbstractBlock {

	public DecorationBlock(String name, int row, int col) {
		super(name, row, col);
		setWalkableStatus(true);
	}

}
