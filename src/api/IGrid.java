package api;

/**
 * The grid interface
 * @author Aninda Manocha, Filip Mazurek
 */

public interface IGrid {

	/**
	 * Gets the number of rows in the grid
	 * @return the number of rows
	 */
	public int getNumRows();

	/**
	 * Gets the number of columns in the grid
	 * @return the number of columns
	 */
	public int getNumCols();

	/**
	 * Gets the block located at a specific cell
	 * @param row - the row of the cell
	 * @param col - the column of the cell
	 * @return the block
	 */
	public Block getBlock(int row, int col);

    /**
     * Sets a block in the grid in a specific location
     * @param row - the row of the block
     * @param col - the column of the block
     * @param block - the new block
     */
	public void setBlock(int row, int col, Block block);
}
