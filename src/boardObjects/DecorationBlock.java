package boardObjects;

import grid.BlockType;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek, Daniel Chai
 */
public class DecorationBlock extends Block implements Walkable {

	public DecorationBlock(String name, int row, int col) {
		super(name, BlockType.DECORATION, row, col);
		setWalkableStatus(IS_WALKABLE);
	}

}
