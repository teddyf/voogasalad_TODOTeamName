package xml;

import java.util.ArrayList;

import block.Block;
import block.BlockType;
import grid.Grid;

/**
 * Saves a whole grid of Blocks to XML.
 * 
 * @author Daniel Chai
 */
public class GridSaver {
	private BlockSaver blockSaver;
	private StringBuilder mySavedGrid;
	
	public GridSaver() {
		blockSaver = new BlockSaver();
		mySavedGrid = new StringBuilder();
	}
	
	public String saveGrid(Grid grid) {
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				Block block = grid.getBlock(row, col);
				String savedBlock = blockSaver.saveBlock(block);
				mySavedGrid.append(savedBlock);
			}
		}
		
		return mySavedGrid.toString();
	}
	
	/**
	 * For testing purposes.
	 */
	public static void main(String[] args) {
		GridSaver gridSaver = new GridSaver();
		Grid grid = new Grid(3, 3);
		
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				grid.setBlock(row, col, "", BlockType.COMMUNICATOR, new ArrayList<Object>());
			}
		}
		
		System.out.println(gridSaver.saveGrid(grid));
	}
}
