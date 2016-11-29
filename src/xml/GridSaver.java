package xml;

import boardObjects.Block;
import boardObjects.CommunicatorBlock;
import grid.Grid;

/**
 * Saves a whole grid of Blocks to XML.
 * 
 * @author Daniel Chai
 */
public class GridSaver {
	private BlockSaver blockSaver;
	private String mySavedGrid;
	
	public GridSaver() {
		blockSaver = new BlockSaver();
		mySavedGrid = "";
	}
	
	public String saveGrid(Grid grid) {
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				Block block = grid.getBlock(row, col);
				mySavedGrid += blockSaver.saveBlock(block);
			}
		}
		
		return mySavedGrid;
	}
	
	public static void main(String[] args) {
		BlockSaver blockSaver = new BlockSaver();
		Block block = new CommunicatorBlock("Test Block", 0, 0);
		
		blockSaver.saveBlock(block);
	}
}
