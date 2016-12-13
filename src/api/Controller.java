package api;

/**
 * The controller interface
 * @author Aninda Manocha
 */

public interface Controller {

    /**
     * Gets the block located in a specific row and column in the grid. The frontend calls this method in order to
     * render a grid block by block.
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    String getBlock(int row, int col);

    /**
     * Changes to a different specified grid, identified by its index in the list of grids
     * @param index - the index of the grid
     */
    void changeGrid(int index);
}
