package api;

import block.BlockType;
import engine.EngineController;
import grid.GridSizeDirection;

import java.util.List;

/**
 * The editor controller interface
 * @author Aninda Manocha
 */

public interface IEditorController extends IController {

    /**
     * Adds a new grid in the editor where the user can place more objects. The grid is initially set to contain only
     * blocks of grass.
     * @param numRows - the number of rows in the new grid
     * @param numCols - the number of columns in the new grid
     */
    void addGrid(int numRows, int numCols);

    /**
     * Changes the size of the current grid in a specified direction and by a specified amount. If the amount is
     * positive, the grid grows and if the amount is negative, the grid shrinks.
     * @param direction - the direction in which the size changes
     * @param amount - the amount by which the grid size in the specified direction should change
     * @return whether or not the grid was changed
     */
    boolean changeGridSize(GridSizeDirection direction, int amount);

    /**
     * Changes a block's properties in the current grid
     * @param name - the image path name of the block
     * @param blockType - the type of block
     * @param row - the row of the new block
     * @param col - the column of the new block
     */
    void addBlock(String name, BlockType blockType, int row, int col);

    /**
     * Adds a message to a block (to be used for communicator blocks). This method returns false if the selected block
     * is not a communicator block.
     * @param message - the message to add
     * @param row - the row of the block
     * @param col - the column of the block
     * @return whether a message was successfully added to the block
     */
    boolean addMessage(String message, int row, int col);

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
    boolean linkBlocks(int row1, int col1, int index1, int row2, int col2, int index2);

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
    boolean unlinkBlocks(int row1, int col1, int index1, int row2, int col2, int index2);

    /**
     * Adds a player to the grid
     * @param names - the image path names of the player (one for each of the four cardinal directions)
     * @param playerName - the name of the player
     * @param row - the row of the player
     * @param col - the column of the player
     * @return whether the player was successfully added
     */
    boolean addPlayer(List<String> names, String playerName, int row, int col);

    /**
     * Adds an attribute for the player
     * @param name - the name of the attribute
     * @param amount - the initial amount of the attribute
     * @param increment - the amount by which the attribute increases
     * @param decrement - the amount by which the attribute decreases
     * @return whether the attribute was successfully added
     */
    boolean addPlayerAttribute(String name, double amount, double increment, double decrement);

    /**
     * Moves the player to a new location on the current grid
     * @param row - the row of the new location
     * @param col - the column of the new location
     * @return whether or not the player was successfully moved
     */
    boolean movePlayer(int row, int col);

    /**
     * Gets the row in which the player is located
     * @return the row
     */
    public int getPlayerRow();

    /**
     * Gets the column in which the player is located
     * @return the column
     */
    public int getPlayerCol();

    /**
     * Saves the editor by taking in the name of the file to contain the information
     * @param file - the name of the file containing the editor information
     */
    void saveEditor(String file);

    /**
     * Loads an editor that is stored in a file containing the editor information
     * @param file - the name of the file containing the editor information
     */
    void loadEditor(String file);

    /**
     * Exports the editor to create a game by taking in the name of the file to contain the information
     * @param file - the name of the file containing the engine information
     */
    void saveEngine(String file);

    /**
     * Creates an engine to run a game while in the editor
     * @return an engine controller to run the tested game
     */
    EngineController runEngine();
}
