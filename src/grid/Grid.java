package grid;

import api.IGrid;
import block.*;
import java.util.Observable;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * The rectangular grid in which all the block ui.scenes.editor.objects may be placed. This back end class serves to
 * be the main hub of action by holding all the blocks in the grid.
 *
 * @author Filip Mazurek, Aninda Manocha, Daniel Chai
 */

@XStreamAlias("grid")
public class Grid extends Observable implements IGrid {
    @XStreamOmitField
    private ResourceBundle myBlockPaths = ResourceBundle.getBundle("resources/properties/block-paths");
    private int myIndex;
    private int myNumRows;
    private int myNumCols;

    @XStreamImplicit
    private Block[][] myGrid;

    public Grid(int index, int numRows, int numColumns) {
        myIndex = index;
        myNumRows = numRows;
        myNumCols = numColumns;
        myGrid = new Block[numRows][numColumns];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < myNumRows; i++) {
            for (int j = 0; j < myNumCols; j++) {
                myGrid[i][j] = new DecorationBlock("resources/images/tiles/ground/grass-1.png", i, j);
            }
        }
    }

    /**
     * Change the size of the grid based on what the user chooses in the editor.
     *
     * @param numRows
     * @param numCols
     * @param rowStart
     * @param rowEnd
     * @param rowOffset
     * @param colStart
     * @param colEnd
     * @param colOffset
     */
    public void resize(int numRows, int numCols, int rowStart, int rowEnd, int rowOffset, int colStart, int colEnd, int colOffset) {
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