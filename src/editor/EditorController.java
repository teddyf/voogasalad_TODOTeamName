package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.CommunicatorBlock;
import engine.EngineController;
import grid.Grid;
import grid.GridGrowthDirection;
import grid.GridWorld;
import grid.RenderedGrid;
import player.Player;
import interactions.Interaction;
import player.PlayerAttribute;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController {
    private BlockFactory blockFactory;
    private GridXMLHandler xmlHandler;
    private GridWorld gridWorld;
    private Grid currentGrid;
    private RenderedGrid renderedGrid;
    private int myNumRows;
    private int myNumColumns;
    private Player player;

    public EditorController() {
        blockFactory = new BlockFactory();
        xmlHandler = new GridXMLHandler();
        gridWorld = new GridWorld();
    }

    // TODO: Frontend needs to change grids afterward
    public void addGrid(int numRows, int numCols) {
        Grid newGrid = new Grid(gridWorld.getNumGrids(), numRows, numCols);
        gridWorld.addGrid(newGrid);
    }

    public void changeGrid(int index) {
        gridWorld.setCurrentIndex(index);
        currentGrid = gridWorld.getCurrentGrid();
        renderedGrid = new RenderedGrid(currentGrid);
        myNumRows =  currentGrid.getNumRows();
        myNumColumns = currentGrid.getNumCols();
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        if(blockType == BlockType.PLAYER_SPAWN) {
            // TODO: delete the existing player spawn, tell the front end that the other spawn was deleted
        }
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
        for(int i = 0; i < myNumRows; i++) {
            for(int j = 0; j < myNumColumns; j++) {
                //System.out.println(currentGrid.getBlock(i,j).getName());
                //System.out.println(renderedGrid.get(i,j));
            }
        }
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

    public void addPlayer(String name, int row, int col) {
        player = new Player(name, row, col, currentGrid.getIndex());

        System.out.println(name + " " + row + " " + col);
        System.out.println("player added");
    }

    public void addPlayerAttribute(String name, double amount) {
        PlayerAttribute playerAttribute = new PlayerAttribute(name, amount);
        player.addAttribute(name, playerAttribute);
    }

    public void movePlayer(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    /** shrinks the grid the appropriate amount from the appropriate direction
     * @param amount: positive int of how much the grid should shrink
     */
    public void shrinkGrid(GridGrowthDirection direction, int amount) {
        int newNumRows = myNumRows;
        int newNumCols = myNumColumns;
        int rowOffset = 0;
        int colOffset = 0;
        switch (direction) {
            case NORTH:
                rowOffset = amount;
                break;
            case SOUTH:
                newNumRows = myNumRows - amount;
                break;
            case EAST:
                newNumCols = myNumColumns - amount;
                break;
            case WEST:
                colOffset = amount;
                break;
        }
        Grid newGrid = new Grid(gridWorld.getCurrentIndex(), newNumRows, newNumCols);
        renderedGrid = new RenderedGrid(newGrid);
        for (int r = rowOffset; r < newNumRows; r++) {
            for (int c = colOffset; c < newNumCols; c++) {
                newGrid.setBlock(r, c, currentGrid.getBlock(r, c));
            }
        }
        currentGrid = newGrid;
        myNumRows = currentGrid.getNumRows();
        myNumColumns = currentGrid.getNumCols();
        // TODO: check if player is being deleted
    }

    public void growGrid(GridGrowthDirection direction, int amount) {
        int newNumRows = myNumRows;
        int newNumCols = myNumColumns;
        int rowOffset = 0;
        int colOffset = 0;
        switch (direction) {
            case NORTH:
                newNumRows = myNumRows + amount;
                rowOffset = amount;
                break;
            case SOUTH:
                newNumRows = myNumRows + amount;
                break;
            case EAST:
                newNumCols = myNumColumns + amount;
                break;
            case WEST:
                newNumCols = myNumColumns + amount;
                colOffset = amount;
                break;
        }
        Grid newGrid = new Grid(gridWorld.getCurrentIndex(), newNumRows, newNumCols);
        renderedGrid = new RenderedGrid(newGrid);
        for (int r = 0; r < myNumRows; r++) {
            for (int c = 0; c < myNumColumns; c++) {
                newGrid.setBlock(r + rowOffset, c + colOffset, currentGrid.getBlock(r, c));
            }
        }
        currentGrid = newGrid;
        myNumRows = currentGrid.getNumRows();
        myNumColumns = currentGrid.getNumCols();
    }

    /*****METHODS FOR FRONTEND TO CALL*****/

    /**
     * Gets the row in which the player is located
     * @return the row
     */
    public int getRow() {
        return myNumRows;
    }

    /**
     * Gets the column in which the player is located
     * @return the column
     */
    public int getCol() {
        return myNumColumns;
    }

    /**
     * Gets the block located in a specific row and column
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return renderedGrid.get(row, col);
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
