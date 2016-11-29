package grid;

import block.Block;
import java.util.Observable;
import java.util.Observer;

/**
 * This is the rendered grid that the frontend reads from in order to update the displayed grid with the appropriate
 * blocks
 * @author Aninda Manocha
 */

public class RenderedGrid implements Observer {
    private Grid myGrid;
    private int myNumRows;
    private int myNumColumns;
    private String[][] renderedGrid;

    public RenderedGrid(Grid grid) {
        myGrid = grid;
        myNumRows = grid.getNumRows();
        myNumColumns = grid.getNumCols();
        renderedGrid = new String[myNumRows][myNumColumns];
        initializeGrid();
    }

    private void initializeGrid() {
        for(int i = 0; i < myNumRows; i++) {
            for(int j = 0; j < myNumColumns; j++) {
                renderedGrid[i][j] = myGrid.getBlock(i, j).getName();
            }
        }
    }

    public String get(int row, int col) {
        return renderedGrid[row][col];
    }

    public void update(Observable grid, Object obj) {
        if (grid instanceof Grid) {
            int row = ((Block)obj).getRow();
            int col = ((Block)obj).getCol();
            renderedGrid[row][col] = ((Block)obj).getName();
        }
    }
}
