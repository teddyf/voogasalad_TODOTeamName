package grid;

import block.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The rectangular grid in which all the block objects may be placed.
 * @author Filip Mazurek, Aninda Manocha
 */
public class Grid implements IGrid {

    private int myNumRows;
    private int myNumColumns;
    private Block[][] myGrid;
    private ShallowBlock[][] myShallowGrid;

    public Grid(int numRows, int numColumns) {
        myNumRows = numRows;
        myNumColumns = numColumns;
        myGrid = new Block[numRows][numColumns];
        myShallowGrid = new ShallowBlock[numRows][numColumns];
        initializeGrid();
    }

    private void initializeGrid() {
        for(int i = 0; i < myNumRows; i++) {
            for(int j = 0; j < myNumColumns; j++) {
                myGrid[i][j] = new DecorationBlock(BlockFactory.DEFAULT_BLOCK, i, j);
                myShallowGrid[i][j] = new DecorationBlock(BlockFactory.DEFAULT_BLOCK, i, j);
            }
        }
    }

    public int getNumRows() {
        return myNumRows;
    }

    public int getNumCols() {
        return myNumColumns;
    }

    public Grid getGrid() {
        return this;
    }

    public Block getBlock(int row, int col) {
        return myGrid[row][col];
    }

    public ShallowBlock[][] getGridForRendering() {
        return myShallowGrid;
    }

    public void setBlock(int row, int col, Block block) {
        myGrid[row][col] = block;
        myShallowGrid[row][col] = block;
    }
}
