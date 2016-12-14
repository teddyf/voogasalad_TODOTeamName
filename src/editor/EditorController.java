package editor;

import api.Controller;
import api.Player;
import block.blocktypes.BlockType;
import engine.EngineController;
import exceptions.*;
import grid.GridManager;
import grid.GridSizeDirection;
import grid.GridWorld;
import player.PlayerInstance;
import player.PlayerManager;
import ui.scenes.editor.GameEditorAlerts;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

import java.util.List;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController implements Controller {

    private GridManager myGridManager;
    private PlayerManager myPlayerManager;
    private GridXMLHandler xmlHandler;
    private GameEditorAlerts myAlerts;

    public EditorController() {
        myGridManager = new GridManager();
        myPlayerManager = new PlayerManager(myGridManager.getCurrentGrid());
        myGridManager.addObserver(myPlayerManager);
        xmlHandler = new GridXMLHandler();
    }

    public void setAlerts(GameEditorAlerts alerts) {
        myAlerts = alerts;
    }

    /* GRID METHODS */

    /**
     * Adds a new grid in the editor where the user can place more objects. The grid is initially set to contain only
     * blocks of grass.
     * @param numRows - the number of rows in the new grid
     * @param numCols - the number of columns in the new grid
     */
    public void addGrid(int numRows, int numCols) {
        myGridManager.addGrid(numRows, numCols);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

    /**
     * Changes to a different specified grid, identified by its index in the list of grids
     * @param index - the index of the grid
     */
    public void changeGrid(int index) {
        myGridManager.changeGrid(index);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

    /**
     * Changes the size of the current grid in a specified direction and by a specified amount. If the amount is
     * positive, the grid grows and if the amount is negative, the grid shrinks.
     * @param direction - the direction in which the size changes
     * @param amount - the amount by which the grid size in the specified direction should change
     * @return whether or not the grid was changed
     */
    public boolean changeGridSize(GridSizeDirection direction, int amount) {
        try {
            return myGridManager.changeGridSize(direction, amount, getPlayerRow(), getPlayerCol());
        } catch (LargeGridException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        } catch (DeletePlayerWarning e) {
            if (myAlerts.warnUser(e.getMessage())) {
                return myGridManager.shrinkGrid(direction, amount);
            }
            return false;
        }
        return false;
    }

    /**
     * Gets the number of rows in the current grid
     * @return the number of rows
     */
    public int getNumRows() {
        return myGridManager.getCurrentGrid().getNumRows();
    }

    /**
     * Gets the number of columns in the current grid
     * @return the number of columns
     */
    public int getNumCols() {
        return myGridManager.getCurrentGrid().getNumCols();
    }

    /**
     * Adds music to the game
     * @param file - the name of the music file
     */
    public void addMusic(String file) {
        myGridManager.addMusic(file);
    }

    /***** BLOCK METHODS *****/

    /**
     * Changes a block's properties in the current grid
     * @param name - the image path name of the block
     * @param blockType - the type of block
     * @param row - the row of the new block
     * @param col - the column of the new block
     */
    public void addBlock(String name, BlockType blockType, int row, int col) {
        try {
            myGridManager.addBlock(name, blockType, row, col);
        }
        catch (BlockCreationException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    /**
     * Adds a message to a block (to be used for communicator blocks). This method returns false if the selected block
     * is not a communicator block.
     * @param message - the message to add
     * @param row - the row of the block
     * @param col - the column of the block
     * @return whether a message was successfully added to the block
     */
    public boolean addMessage(String message, int row, int col) {
        return (myGridManager.addMessage(message, row, col));
    }

    /**
     * Change the first setting of the gate in editor. Must set the status to open or closed when making a gate
     * @param row - row of the gate block
     * @param col - column of the gate block
     * @param isOpen - true if open the gate, false if close the gate
     * @return if the gate status was set correctly
     */
    public boolean setGateStatus(int row, int col, boolean isOpen) {
        return myGridManager.setGateStatus(row, col, isOpen);
    }

    /**
     * Links two blocks (to be used for teleportation or switches). This method returns false if the selected blocks are
     * not linkable (the wrong type of blocks).
     * @param row1 - the row of the first block
     * @param col1 - the column of the first block
     * @param index1 - the grid index in which the first block is located
     * @param row2 - the row of the second block
     * @param col2 - the column of the second block
     * @param index2 - the grid index in which the second block is located
     * @return whether the blocks were successfully linked
     */
    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.linkBlocks(row1, col1, index1, row2, col2, index2);
    }

    /**
     * Unlinks two blocks that were previously linked. This method returns false if the selected blocks are not
     * unlinkable blocks (either the wrong type or the blocks were not linked to begin with).
     * @param row1 - the row of the first block
     * @param col1 - the column of the first block
     * @param index1 - the grid index in which the first block is located
     * @param row2 - the row of the second block
     * @param col2 - the column of the second block
     * @param index2 - the grid index in which the second block is located
     * @return whether the blocks were successfully unlinked
     */
    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.unlinkBlocks(row1, col1, index1, row2, col2, index2);
    }

    /**
     * Gets the block located in a specific row and column in the grid. The frontend calls this method in order to
     * render a grid block by block.
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return myGridManager.getBlock(row, col);
    }

    /***** PLAYER METHODS *****/

    /**
     * Adds a player to the grid
     * @param names - the image path names of the player (one for each of the four cardinal directions)
     * @param playerName - the name of the player
     * @param row - the row of the player
     * @param col - the column of the player
     * @return whether the player was successfully added
     */
    public boolean addPlayer(List<String> names, String playerName, int row, int col) {
        try {
            return myPlayerManager.addPlayer(names, playerName, row, col, myGridManager.getCurrentIndex());
        }
        catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
        catch (DuplicatePlayerException e2) {
            myAlerts.exceptionDisplay(e2.getMessage());
            return false;
        }
    }

    /**
     * Adds an attribute for the player
     * @param name - the name of the attribute
     * @param amount - the initial amount of the attribute
     * @param increment - the amount by which the attribute increases
     * @param decrement - the amount by which the attribute decreases
     * @return whether the attribute was successfully added
     */
    public boolean addPlayerAttribute(String name, double amount, double increment, double decrement) {
        try {
            return myPlayerManager.addPlayerAttribute(name, amount, increment, decrement);
        } catch (DuplicateAttributeException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    /**
     * Deletes the player
     */
    public void deletePlayer() {
        myPlayerManager.deletePlayer();
    }

    /**
     * Moves the player to a new location on the current grid
     * @param row - the row of the new location
     * @param col - the column of the new location
     * @return whether or not the player was successfully moved
     */
    public boolean movePlayer(int row, int col) {
        try {
            return myPlayerManager.movePlayer(row, col);
        } catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public int getPlayerRow() {
        return myPlayerManager.getRow();
    }

    /**
     * Gets the column in which the player is located
     * @return the column
     */
    public int getPlayerCol() {
        return myPlayerManager.getCol();
    }

    /***** DATA METHODS *****/

    /**
     * Saves the editor by taking in the name of the file to contain the information
     * @param file - the name of the file containing the editor information
     */
    public void saveEditor(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            xmlHandler.saveContents(file, gridWorld, null);
        }
    }

    /**
     * Loads an editor that is stored in a file containing the editor information
     * @param file - the name of the file containing the editor information
     */
    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        myPlayerManager.setPlayer(gridWorldAndPlayer.getPlayer());
        myGridManager = new GridManager(gridWorldAndPlayer.getGridWorld().getGrids());
        changeGrid(myGridManager.getCurrentIndex());
    }

    /**
     * Exports the editor to create a game by taking in the name of the file to contain the information
     * @param file - the name of the file containing the engine information
     */
    public void saveEngine(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    /**
     * Creates an engine to run a game while in the editor
     * @return an engine controller to run the tested game
     */
    public EngineController runEngine() {
        try {
            Player enginePlayer = new PlayerInstance(myPlayerManager.getPlayer());
            GridManager engineGridManager = myGridManager.deepClone();
            EngineController testEngineController = new EngineController(enginePlayer, myGridManager, engineGridManager.getMusic());
            myGridManager = engineGridManager;
            return testEngineController;
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return null;
        }
    }
}