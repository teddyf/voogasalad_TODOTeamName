package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.CommunicatorBlock;
import engine.EngineController;
import exceptions.*;
import grid.Grid;
import grid.GridGrowthDirection;
import grid.GridWorld;
import player.Player;
import player.PlayerAttribute;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorModel {
    private BlockFactory blockFactory;
    private GridXMLHandler xmlHandler;
    private GridWorld gridWorld;
    private Grid currentGrid;
    private Player player;

    public EditorModel() {
        blockFactory = new BlockFactory();
        xmlHandler = new GridXMLHandler();
        gridWorld = new GridWorld();
    }

    public void addGrid(int numRows, int numCols) {
        currentGrid = gridWorld.addGrid(numRows, numCols);
    }

    public void changeGrid(int index) {
        currentGrid = gridWorld.changeGrid(index);
    }

    public boolean changeGridSize(GridGrowthDirection direction, int amount) throws LargeGridException, DeletePlayerWarning {
        if (amount >= 0) {
            return growGrid(direction, amount);
        }
        return shrinkGrid(direction, amount);
    }

    private boolean deletePlayer() {
        return false;
    }

    /** shrinks the grid the appropriate amount from the appropriate direction
     * @param amount: positive int of how much the grid should shrink
     */
    public boolean shrinkGrid(GridGrowthDirection direction, int amount) throws DeletePlayerWarning {
        int numRows, numCols, rowOffset, colOffset, rowStart, rowEnd, colStart, colEnd;
        numRows = rowEnd = currentGrid.getNumRows();
        numCols = colEnd = currentGrid.getNumCols();
        rowOffset = colOffset = rowStart = colStart = 0;

        switch (direction) {
            case NORTH:
                if (player.getRow() < amount) {
                    throw new DeletePlayerWarning();
                }
                numRows -= amount;
                rowOffset = amount;
                player.setRow(player.getRow() - rowOffset);
                break;
            case SOUTH:
                numRows -= amount;
                if(player.getRow() > numRows) {
                    return false;
                }
                break;
            case EAST:
                numCols -= amount;
                if(player.getCol() > numCols) {
                    return false;
                }
                break;
            case WEST:
                if(player.getCol() < amount) {
                    return false;
                }
                numCols -= amount;
                colOffset = amount;
                player.setCol(player.getCol() - colOffset);
                break;
        }
        currentGrid.resize(numRows, numCols, rowStart, rowEnd, rowOffset, colStart, colEnd, colOffset);
        return true;
    }

    public boolean growGrid(GridGrowthDirection direction, int amount) throws LargeGridException{
        int numRows, numCols, rowOffset, colOffset, rowStart, rowEnd, colStart, colEnd;
        numRows = rowEnd = currentGrid.getNumRows();
        numCols = colEnd = currentGrid.getNumCols();
        rowOffset = colOffset = rowStart = colStart = 0;

        switch (direction) {
            case NORTH:
                rowOffset = -amount;
                numRows += amount;
                break;
            case SOUTH:
                rowEnd = numRows;
                numRows += amount;
                break;
            case EAST:
                colEnd = numCols;
                numCols += amount;
                break;
            case WEST:
                colOffset = -amount;
                numCols += amount;
                break;
        }

        if (numRows > 100 || numCols > 100) {
            throw new LargeGridException();
        }
        currentGrid.resize(numRows, numCols, rowOffset, rowEnd, rowOffset, colOffset, colEnd, colOffset);
        return true;
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
    }

    public boolean addMessage(String message, int row, int col) {
        Block block = currentGrid.getBlock(row, col);
        if(block instanceof CommunicatorBlock) {
            ((CommunicatorBlock) block).setMessage(message);
            return true;
        }
        return false;
    }

    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        Grid grid1 = gridWorld.getGrid(index1);
        Grid grid2 = gridWorld.getGrid(index2);
        Block block1 = grid1.getBlock(row1, col1);
        Block block2 = grid2.getBlock(row2, col2);
        return (block1.link(block2, index2) || block2.link(block1, index1));
    }

    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        Grid grid1 = gridWorld.getGrid(index1);
        Grid grid2 = gridWorld.getGrid(index2);
        Block block1 = grid1.getBlock(row1, col1);
        Block block2 = grid2.getBlock(row2, col2);
        return (block1.unlink(block2) || block2.unlink(block2));
    }

    public boolean addPlayer(String name, int row, int col) throws BadPlayerPlacementException, DuplicatePlayerException {
        if(!(currentGrid.getBlock(row, col).isWalkable())) {
            throw new BadPlayerPlacementException(row, col);
        }
        if(player == null) {
            player = new Player(name, row, col, currentGrid.getIndex());
            return true;
        }
        else {
            throw new DuplicatePlayerException(player.getRow(), player.getCol());
        }
    }

    public void addPlayerAttribute(String name, double amount, double increment, double decrement) {
        PlayerAttribute playerAttribute = new PlayerAttribute(name, amount, increment, decrement);
        player.addAttribute(playerAttribute);
    }

    public void movePlayer(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    /*****METHODS FOR FRONTEND TO CALL*****/

    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the block located in a specific row and column
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return currentGrid.getBlock(row, col).getName();
    }

    /**
     * Saves the editor by taking in the name of the file to contain the information and calling the data handling
     * method
     * @param file - the name of the file containing the editor information
     */
    public void saveEditor(String file) {
        xmlHandler.saveContents(file, gridWorld, player);
    }

    /**
     * Loads an editor that is stored in a specific file by calling the data handling method and storing the grid world
     * and player
     * @param file - the specific file
     */
    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        player = gridWorldAndPlayer.getPlayer();
        gridWorld = gridWorldAndPlayer.getGridWorld();
        changeGrid(gridWorld.getCurrentIndex());
    }

    /**
     *
     * @param file
     */
    public void saveEngine(String file) throws NoPlayerException {
        if (player == null) {
            throw new NoPlayerException();
        }
        xmlHandler.saveContents(file, gridWorld, player);
    }

    public EngineController runEngine() {
        return (new EngineController(player, gridWorld));
    }
}
