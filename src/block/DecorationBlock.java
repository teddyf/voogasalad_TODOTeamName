package block;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek, Daniel Chai, Aninda Manocha
 */
public class DecorationBlock extends Block implements Walkable {

	public DecorationBlock(String name, int row, int col) {
		super(name, row, col);
		setWalkableStatus(IS_WALKABLE);
	}

}
