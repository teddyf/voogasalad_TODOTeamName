package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.CommunicatorBlock;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import exceptions.BlockCreationException;
import exceptions.DeletePlayerWarning;
import exceptions.LargeGridException;
import player.PlayerBlockUpdate;
import player.PlayerUpdate;

/**
 * This class manages all of the grids in the editor or engine
 * @author Aninda Manocha, Daniel Chai
 */

@XStreamAlias("gridManager")
public class GridManager extends Observable {

    private static final String SIZE_CHOOSER = "resources/properties/size-chooser";

	@XStreamImplicit
    private List<Grid> grids;
    
    @XStreamOmitField
    private int currentIndex;

    private ResourceBundle myResources;
    private Grid currentGrid;
    private BlockFactory blockFactory;

    public GridManager() {
        grids = new ArrayList<>();
        currentIndex = 0;
        myResources = ResourceBundle.getBundle(SIZE_CHOOSER);
        blockFactory = new BlockFactory();
    }

    public void addGrid(int numRows, int numCols) {
        Grid newGrid = new Grid(grids.size(), numRows, numCols);
        grids.add(newGrid);
        changeGrid(grids.size() -1);
    }

    public void changeGrid(int index) {
        currentIndex = index;
        currentGrid = grids.get(currentIndex);
    }

    public boolean changeGridSize(GridSizeDirection direction, int amount, int playerRow, int playerColumn) throws LargeGridException, DeletePlayerWarning {
        if (amount >= 0) {
            return growGrid(direction, amount);
        }
        return checkShrink(direction, amount, playerRow, playerColumn);
    }
    /**
     * Determines if the grid can shrink without deleting the player. If not, the user receives a warning about deleting
     * the player.
     * @param direction - the direction in which to shrink the grid
     * @param amount - the amount by which the grid size should shrink
     * @return whether the grid can shrink without deleting the player
     * @throws DeletePlayerWarning
     */
    private boolean checkShrink(GridSizeDirection direction, int amount, int playerRow, int playerColumn) throws DeletePlayerWarning {
        switch (direction) {
            case TOP:
                if (playerRow < amount) {
                    throw new DeletePlayerWarning();
                }
            case BOTTOM:
                if(playerRow >= currentGrid.getNumRows() - amount) {
                    throw new DeletePlayerWarning();
                }
            case RIGHT:
                if(playerColumn >= currentGrid.getNumCols() - amount) {
                    throw new DeletePlayerWarning();
                }
            case LEFT:
                if(playerColumn < amount) {
                    throw new DeletePlayerWarning();
                }
        }
        return shrinkGrid(direction, amount);
    }

    /**
     * Decreases the size of the current grid in a specified direction and by a specified amount
     * @param direction - the direction in which the size changes
     * @param amount - the amount by which the grid size in the specified direction should shrink
     * @return whether or not the grid shrunk
     */
    public boolean shrinkGrid(GridSizeDirection direction, int amount) {
        int numRows, numCols, rowOffset, colOffset, rowStart, rowEnd, colStart, colEnd;
        numRows = rowEnd = currentGrid.getNumRows();
        numCols = colEnd = currentGrid.getNumCols();
        rowOffset = colOffset = rowStart = colStart = 0;
        PlayerBlockUpdate playerBlockUpdate = null;
        switch (direction) {
            case TOP:
                numRows -= amount;
                rowOffset = amount;
                playerBlockUpdate = new PlayerBlockUpdate(PlayerUpdate.ROW, rowOffset);
                setChanged();
                break;
            case BOTTOM:
                numRows -= amount;
                break;
            case RIGHT:
                numCols -= amount;
                break;
            case LEFT:
                numCols -= amount;
                colOffset = amount;
                playerBlockUpdate = new PlayerBlockUpdate(PlayerUpdate.COLUMN, colOffset);
                setChanged();
                break;
        }
        currentGrid.resize(numRows, numCols, rowStart, rowEnd, rowOffset, colStart, colEnd, colOffset);
        notifyObservers(playerBlockUpdate);
        return true;
    }

    /**
     * Increases the size of the current grid in a specified direction and by a specified amount
     * @param direction - the direction in which the size changes
     * @param amount - the amount by which the grid size in the specified direction should grow
     * @return whether or not the grid grew
     * @throws LargeGridException
     */
    private boolean growGrid(GridSizeDirection direction, int amount) throws LargeGridException{
        int numRows, numCols, rowOffset, colOffset, rowStart, rowEnd, colStart, colEnd;
        numRows = rowEnd = currentGrid.getNumRows();
        numCols = colEnd = currentGrid.getNumCols();
        rowOffset = colOffset = rowStart = colStart = 0;
        switch (direction) {
            case TOP:
                rowOffset = -amount;
                numRows += amount;
                break;
            case BOTTOM:
                rowEnd = numRows;
                numRows += amount;
                break;
            case RIGHT:
                colEnd = numCols;
                numCols += amount;
                break;
            case LEFT:
                colOffset = -amount;
                numCols += amount;
                break;
        }
        int maxDim = Integer.parseInt(myResources.getString("maxDim"));
        if (numRows > maxDim || numCols > maxDim) {
            throw new LargeGridException();
        }
        currentGrid.resize(numRows, numCols, rowStart, rowEnd, rowOffset, colStart, colEnd, colOffset);
        return true;
    }

    public void addBlock(String name, BlockType blockType, int row, int col) throws BlockCreationException {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
    }

    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        Grid grid1 = grids.get(index1);
        Grid grid2 = grids.get(index2);
        Block block1 = grid1.getBlock(row1, col1);
        Block block2 = grid2.getBlock(row2, col2);
        boolean firstLink = block1.link(block2, index2);
        boolean secondLink = block2.link(block1, index1);
        return (firstLink || secondLink);
    }

    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        Grid grid1 = grids.get(index1);
        Grid grid2 = grids.get(index2);
        Block block1 = grid1.getBlock(row1, col1);
        Block block2 = grid2.getBlock(row2, col2);
        return (block1.unlink(block2) || block2.unlink(block2));
    }

    public boolean addMessage(String message, int row, int col) {
        Block block = currentGrid.getBlock(row, col);
        if(block instanceof CommunicatorBlock) {
            ((CommunicatorBlock) block).setMessage(message);
            return true;
        }
        return false;
    }

    /***** GETTERS *****/

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public String getBlock(int row, int col) {
        return currentGrid.getBlock(row, col).getName();
    }
}