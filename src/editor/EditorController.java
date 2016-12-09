package editor;

import block.BlockType;
import engine.EngineController;
import exceptions.BadPlayerPlacementException;
import exceptions.DuplicatePlayerException;
import exceptions.LargeGridException;
import exceptions.NoPlayerException;
import grid.GridGrowthDirection;
import ui.scenes.editor.sidemenu.GameEditorAlerts;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController {

    private final EditorModel myModel;
    private GameEditorAlerts myAlerts;

    public EditorController() {
        myModel = new EditorModel();
    }

    // not backend's fault
    public void setAlerts(GameEditorAlerts alerts) {
        myAlerts = alerts;
    }

    public void addGrid(int numRows, int numCols) {
        myModel.addGrid(numRows, numCols);
    }

    public void changeGrid(int index) {
        myModel.changeGrid(index);
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        myModel.addBlock(name, blockType, row, col);
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
        return unlinkBlocks(row1, col1, index1, row2, col2, index2);
    }

    public boolean addPlayer(String name, int row, int col) {
        try {
            return myModel.addPlayer(name, row, col);
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

    public void addPlayerAttribute(String name, double amount, double increment, double decrement) {
        myModel.addPlayerAttribute(name, amount, increment, decrement);
    }

    public void movePlayer(int row, int col) {
        myModel.movePlayer(row, col);
    }

    /** shrinks the grid the appropriate amount from the appropriate direction
     * @param amount: positive int of how much the grid should shrink
     */
    public void shrinkGrid(GridGrowthDirection direction, int amount) {
        //myModel.shrinkGrid(direction, amount);
    }

    public void growGrid(GridGrowthDirection direction, int amount) {
        try {
            myModel.growGrid(direction, amount);
        }
        catch(LargeGridException e) {
            myAlerts.exceptionDisplay(e.getMessage());
        }
    }



    /*****METHODS FOR FRONTEND TO CALL*****/

    /**
     * Gets the row in which the player is located
     * @return the row
     */
    public int getRow() {
        return myModel.getPlayer().getRow();
    }

    /**
     * Gets the column in which the player is located
     * @return the column
     */
    public int getCol() {
        return myModel.getPlayer().getCol();
    }

    /**
     * Gets the block located in a specific row and column
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return myModel.getBlock(row, col);
    }

    /**
     * Saves the editor by taking in the name of the file to contain the information and calling the data handling
     * method
     * @param file - the name of the file containing the editor information
     */
    public void saveEditor(String file) {
        myModel.saveEditor(file);
    }

    /**
     * Loads an editor that is stored in a specific file by calling the data handling method and storing the grid world
     * and player
     * @param file - the specific file
     */
    public void loadEditor(String file) {
        myModel.loadEditor(file);
    }

    /**
     *
     * @param file
     */
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
