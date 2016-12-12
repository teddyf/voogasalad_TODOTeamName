package editor;

import api.IEditorController;
import block.BlockFactory;
import block.BlockType;
import engine.EngineController;
import exceptions.*;
import grid.Grid;
import grid.GridManager;
import grid.GridSizeDirection;
import grid.GridWorld;
import player.Player;
import player.PlayerManager;
import ui.scenes.editor.GameEditorAlerts;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController implements IEditorController {

    private GridManager myGridManager;
    private PlayerManager myPlayerManager;
    private GridXMLHandler xmlHandler;

    private GameEditorAlerts myAlerts;

    public EditorController() {
        myGridManager = new GridManager();
        myPlayerManager = new PlayerManager(myGridManager.getCurrentGrid());
        try {
            System.out.println(myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            System.out.println(e);
        }
        xmlHandler = new GridXMLHandler();
        myGridManager.addObserver(myPlayerManager);
    }

    public void setAlerts(GameEditorAlerts alerts) {
        myAlerts = alerts;
    }

    /***** GRID METHODS *****/

    public void addGrid(int numRows, int numCols) {
        myGridManager.addGrid(numRows, numCols);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

    public void changeGrid(int index) {
        myGridManager.changeGrid(index);
        myPlayerManager.setGrid(myGridManager.getCurrentGrid());
    }

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

    public void addMusic(String file) {
        myGridManager.addMusic(file);
    }

    /***** BLOCK METHODS *****/

    public void addBlock(String name, BlockType blockType, int row, int col) {
        try {
            myGridManager.addBlock(name, blockType, row, col);
        }
        catch (BlockCreationException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    public boolean addMessage(String message, int row, int col) {
        return (myGridManager.addMessage(message, row, col));
    }

    /**
     * Change the first setting of the gate in editor. Must set the status to open or closed when making a gate
     * @param row: row of the gate block
     * @param col: column of the gate block
     * @param isOpen: true if open the gate, false if close the gate
     * @return if the gate status was set correctly
     */
    public boolean setGateStatus(int row, int col, boolean isOpen) {
        return myGridManager.setGateStatus(row, col, isOpen);
    }

    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.linkBlocks(row1, col1, index1, row2, col2, index2);
    }

    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myGridManager.unlinkBlocks(row1, col1, index1, row2, col2, index2);
    }

    public String getBlock(int row, int col) {
        return myGridManager.getBlock(row, col);
    }

    /***** PLAYER METHODS *****/

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

    public boolean addPlayerAttribute(String name, double amount, double increment, double decrement) {
        try {
            return myPlayerManager.addPlayerAttribute(name, amount, increment, decrement);
        } catch (DuplicateAttributeException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public void deletePlayer() {
        myPlayerManager.deletePlayer();
    }

    public boolean movePlayer(int row, int col) {
        try {
            return myPlayerManager.movePlayer(row, col);
        } catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public int getPlayerRow() {
        try {
            return myPlayerManager.getPlayer().getRow();
        } catch (NoPlayerException e) {
            return -1;
        }
    }

    public int getPlayerCol() {
        try {
            return myPlayerManager.getPlayer().getCol();
        } catch (NoPlayerException e) {
            return -1;
        }
    }

    /***** DATA METHODS *****/

    public void saveEditor(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            xmlHandler.saveContents(file, gridWorld, null);
        }
    }

    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        myPlayerManager.setPlayer(gridWorldAndPlayer.getPlayer());
        myGridManager = new GridManager(gridWorldAndPlayer.getGridWorld().getGrids());
        changeGrid(myGridManager.getCurrentIndex());
    }

    public void saveEngine(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        try {
            xmlHandler.saveContents(file, gridWorld, myPlayerManager.getPlayer());
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    public EngineController runEngine() {
        try {
            Player testPlayer = new Player(myPlayerManager.getPlayer());
            System.out.println("i am sad " + myGridManager.getGrids().get(0).getBlock(0,0));
            GridManager testGridManager = myGridManager.deepClone();
            return (new EngineController(testPlayer, testGridManager));
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return null;
        }
    }
}