package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.CommunicatorBlock;
import engine.EngineController;
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
        Grid newGrid = new Grid(gridWorld.getNumGrids(), numRows, numCols);
        gridWorld.addGrid(newGrid);
        changeGrid(gridWorld.getNumGrids()-1);
    }

    public void changeGrid(int index) {
        gridWorld.setCurrentIndex(index);
        currentGrid = gridWorld.getCurrentGrid();
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

    public boolean addPlayer(String name, int row, int col) {
        if(player == null) {
            player = new Player(name, row, col, currentGrid.getIndex());
            return true;
        }
        //TODO: Error message saying "player already exists; delete player to add a new one"
        //TODO front end needs to take care of this as they will need to delete the old player and set a new image on grid
        return false;
    }

    public void addPlayerAttribute(String name, double amount, double increment, double decrement) {
        PlayerAttribute playerAttribute = new PlayerAttribute(name, amount, increment, decrement);
        player.addAttribute(playerAttribute);
    }

    public void movePlayer(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    /** shrinks the grid the appropriate amount from the appropriate direction
     * @param amount: positive int of how much the grid should shrink
     */
    public void shrinkGrid(GridGrowthDirection direction, int amount) {
        int newNumRows = currentGrid.getNumRows();
        int newNumCols = currentGrid.getNumCols();
        int rowOffset = 0;
        int colOffset = 0;
        switch (direction) {
            case NORTH:
                rowOffset = amount;
                if(player.getRow() < amount) {

                    //TODO warning message "your player is out of bounds and is about to be deleted. Continue?"
                    // if yes, player = null, continue
                    // if no, return

                }
                else {
                    player.setRow(player.getRow() - rowOffset);
                }
                break;
            case SOUTH:
                newNumRows = currentGrid.getNumRows() - amount;
                if(player.getRow() < newNumRows) {
                    //TODO warning message
                }
                break;
            case EAST:
                newNumCols = currentGrid.getNumCols() - amount;
                if(player.getCol() < newNumCols) {
                    //TODO warning message
                }
                break;
            case WEST:
                colOffset = amount;
                if(player.getCol() < amount) {
                    //TODO warning message
                }
                else {
                    player.setCol(player.getCol() - colOffset);
                }
                break;
        }

        Grid newGrid = new Grid(gridWorld.getCurrentIndex(), newNumRows - rowOffset, newNumCols - colOffset);
        for (int r = rowOffset; r < newNumRows; r++) {
            for (int c = colOffset; c < newNumCols; c++) {
                newGrid.setBlock(r, c, currentGrid.getBlock(r, c));
            }
        }
        currentGrid = newGrid;
    }

    public void growGrid(GridGrowthDirection direction, int amount) {
        int newNumRows = currentGrid.getNumRows();
        int newNumCols = currentGrid.getNumCols();
        int rowOffset = 0;
        int colOffset = 0;
        switch (direction) {
            case NORTH:
                newNumRows = currentGrid.getNumRows() + amount;
                rowOffset = amount;
                break;
            case SOUTH:
                newNumRows = currentGrid.getNumRows() + amount;
                break;
            case EAST:
                newNumCols = currentGrid.getNumCols() + amount;
                break;
            case WEST:
                newNumCols = currentGrid.getNumCols() + amount;
                colOffset = amount;
                break;
        }
        Grid newGrid = new Grid(gridWorld.getCurrentIndex(), newNumRows, newNumCols);
        for (int r = 0; r < currentGrid.getNumRows(); r++) {
            for (int c = 0; c < currentGrid.getNumCols(); c++) {
                newGrid.setBlock(r + rowOffset, c + colOffset, currentGrid.getBlock(r, c));
            }
        }
        currentGrid = newGrid;
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
    public void saveEngine(String file) {
        xmlHandler.saveContents(file, gridWorld, player);
        System.out.println(player);
    }

    public EngineController runEngine() {
        return (new EngineController(player, gridWorld));
    }
}
