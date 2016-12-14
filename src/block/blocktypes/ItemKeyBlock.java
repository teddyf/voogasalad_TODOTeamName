package block.blocktypes;


import block.blocktypes.ItemBlock;

/**
 * a key that is picked up by the player when walked upon
 * 
 * @author Ryan Anders
 */
public class ItemKeyBlock extends ItemBlock {

	public ItemKeyBlock(String name, int row, int col) {
		super(name, row, col);
		setWalkableStatus(true);
	}

}