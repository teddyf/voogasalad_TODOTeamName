package grid;

import api.IGrid;
import block.*;
import java.util.Observable;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * The rectangular grid in which all the block objects may be placed.
 * @author Filip Mazurek, Aninda Manocha, Daniel Chai
 */

@XStreamAlias("grid")
public class Grid extends Observable implements IGrid {
    private ResourceBundle myBlockPaths = ResourceBundle.getBundle("resources/properties/block-paths");
    private int myNumRows;
    private int myNumColumns;
    
    @XStreamImplicit
    private Block[][] myGrid;

    public Grid(int numRows, int numColumns) {
        myNumRows = numRows;
        myNumColumns = numColumns;
        myGrid = new Block[numRows][numColumns];
        initializeGrid();
    }

    private void initializeGrid() {
        for(int i = 0; i < myNumRows; i++) {
            for(int j = 0; j < myNumColumns; j++) {
                myGrid[i][j] = new DecorationBlock(BlockType.DECORATION, "resources/Default.png", i, j);
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

    public void setBlock(int row, int col, Block block) {
        System.out.println("here");
        myGrid[row][col] = block;
        setChanged();
        notifyObservers(block);
    }
}
