package api;

import java.io.Serializable;

/**
 * The grid interface, The rectangular grid in which all the block ui.scenes.editor.objects may be placed. This back end
 * class serves to be the main hub of action by holding all the blocks in the grid.
 *
 * @author Aninda Manocha, Filip Mazurek
 */

public interface Grid extends Serializable {

    /**
     * Change the size of the grid based on what the user chooses in the editor.
     *
     * @param numRows the number of rows in the new grid
     * @param numCols the number of columns in the new grid
     * @param rowStart the row on which to start in the original grid
     * @param rowEnd the row on which to end in the original grid
     * @param rowOffset the offset where to place the original grid on the new grid
     * @param colStart the column on which to start in the original grid
     * @param colEnd the column on which to end in the original grid
     * @param colOffset the offset where to place the original grid on the new grid
     */
    void resize(int numRows, int numCols, int rowStart, int rowEnd,
                int rowOffset, int colStart, int colEnd, int colOffset);

	/**
	 * Gets the number of rows in the grid
     *
	 * @return the number of rows
	 */
	int getNumRows();

	/**
	 * Gets the number of columns in the grid
     *
	 * @return the number of columns
	 */
	int getNumCols();

	/**
	 * Gets the block located at a specific cell
     *
	 * @param row - the row of the cell
	 * @param col - the column of the cell
	 * @return the block
	 */
	Block getBlock(int row, int col);

    /**
     * Sets a block in the grid in a specific location
     *
     * @param row - the row of the block
     * @param col - the column of the block
     * @param block - the new block
     */
	void setBlock(int row, int col, Block block);

    /**
     * Get the index of the grid, used for multiple grids
     *
     * @return index
     */
    int getIndex();
}
