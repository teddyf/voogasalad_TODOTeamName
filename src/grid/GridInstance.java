package grid;

import api.Block;
import api.Grid;
import java.util.Observable;
import java.util.ResourceBundle;

import block.blocktypes.DecorationBlock;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Instance of a grid.
 *
 * @author Filip Mazurek, Aninda Manocha, Daniel Chai
 */

@XStreamAlias("grid")
public class GridInstance extends Observable implements Grid {
    @XStreamOmitField
    private ResourceBundle myBlockPaths = ResourceBundle.getBundle("resources/properties/block-paths");
    
    private int myIndex;
    private int myNumRows;
    private int myNumCols;

    @XStreamImplicit
    private Block[][] myGrid;

    public GridInstance(int index, int numRows, int numColumns) {
        myIndex = index;
        myNumRows = numRows;
        myNumCols = numColumns;
        myGrid = new Block[numRows][numColumns];
        initializeGrid();
    }

    /**
     * Prepare a blank grid for all blocks to be placed. The default is a DecorationBlock grass tile.
     */
    private void initializeGrid() {
        for (int i = 0; i < myNumRows; i++) {
            for (int j = 0; j < myNumCols; j++) {
                myGrid[i][j] = new DecorationBlock("resources/images/tiles/ground/grass-1.png", i, j);
            }
        }
        System.out.println("backend grid " + myNumRows + "backend grid " + myNumCols);
    }
    // TODO: put that stupid resources path into a properties file.


    public void resize(int numRows, int numCols, int rowStart, int rowEnd,
                       int rowOffset, int colStart, int colEnd, int colOffset) {
        Block[][] newGrid = new Block[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row < rowStart || row >= rowEnd || col < colStart || col >= colEnd) {
                    newGrid[row][col] = new DecorationBlock("resources/images/tiles/ground/grass-1.png", row, col);
                } else {
                    newGrid[row][col] = myGrid[row + rowOffset][col + colOffset];
                }
            }
        }
        myGrid = newGrid;
    }

    public int getIndex() {
        return myIndex;
    }

    public int getNumRows() {
        return myNumRows;
    }

    public int getNumCols() {
        return myNumCols;
    }

    public Block getBlock(int row, int col) {
        if (row < 0 || row >= myNumRows || col < 0 || col >= myNumCols) {
            return null;
        } else {
            return myGrid[row][col];
        }
    }

    public void setBlock(int row, int col, Block block) {
        myGrid[row][col] = block;
        setChanged();
        notifyObservers(block);
    }
}