package editor;

import api.IEditorController;
import block.BlockType;
import editor.EditorModel;
import engine.EngineController;
import exceptions.*;
import grid.GridGrowthDirection;
import ui.scenes.editor.GameEditorAlerts;

import java.util.List;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController implements IEditorController {

    private final EditorModel myModel;
    private GameEditorAlerts myAlerts;

    public EditorController() {
        myModel = new EditorModel();
    }

    public void setAlerts(GameEditorAlerts alerts) {
        myAlerts = alerts;
    }

    /***** GRID METHODS *****/

    public void addGrid(int numRows, int numCols) {
        myModel.addGrid(numRows, numCols);
    }

    public void changeGrid(int index) {
        myModel.changeGrid(index);
    }

    public boolean changeGridSize(GridGrowthDirection direction, int amount) {
        try {
            return myModel.changeGridSize(direction, amount);
        } catch (LargeGridException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        } catch (DeletePlayerWarning e) {
            if (myAlerts.warnUser(e.getMessage())) {
                return myModel.shrinkGrid(direction, amount);
            }
            return false;
        }
        return false;
    }

    /***** BLOCK METHODS *****/

    public void addBlock(String name, BlockType blockType, int row, int col) {
        try {
            myModel.addBlock(name, blockType, row, col);
        }
        catch (BlockCreationException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    public boolean addMessage(String message, int row, int col) {
        if(addMessage(message, row, col)) {
            return true;
        }
//        myAlerts.exceptionDisplay();
        return false;
    }

    public boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myModel.linkBlocks(row1, col1, index1, row2, col2, index2);
    }

    public boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2) {
        return myModel.unlinkBlocks(row1, col1, index1, row2, col2, index2);
    }

    public String getBlock(int row, int col) {
        return myModel.getBlock(row, col);
    }

    /***** PLAYER METHODS *****/

    public boolean addPlayer(List<String> names, String playerName, int row, int col) {
        try {
            return myModel.addPlayer(names, playerName, row, col);
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
            return myModel.addPlayerAttribute(name, amount, increment, decrement);
        } catch (DuplicateAttributeException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public void deletePlayer() {
        myModel.deletePlayer();
    }

    public boolean movePlayer(int row, int col) {
        try {
            return myModel.movePlayer(row, col);
        } catch (BadPlayerPlacementException e) {
            myAlerts.exceptionDisplay(e.getMessage());
            return false;
        }
    }

    public int getPlayerRow() {
        return myModel.getPlayer().getRow();
    }

    public int getPlayerCol() {
        return myModel.getPlayer().getCol();
    }

    /***** DATA METHODS *****/

    public void saveEditor(String file) {
        myModel.saveEditor(file);
    }

    public void loadEditor(String file) {
        myModel.loadEditor(file);
    }

    public void saveEngine(String file) {
        try {
            myModel.saveEngine(file);
        } catch (NoPlayerException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }

    public EngineController runEngine() {
        return myModel.runEngine();
    }
}
