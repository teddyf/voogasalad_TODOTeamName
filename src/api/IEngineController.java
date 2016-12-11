package api;

import block.BlockUpdate;
import engine.UserInstruction;

import java.util.List;
import java.util.Observable;

/**
 * The engine controller interface
 * @author Aninda Manocha
 */

public interface IEngineController extends IController {

    /***** GRID METHODS *****/

    /**
     * Gets the number of rows in the current grid in order for the frontend to draw the grid
     * @return the number of rows
     */
    int getNumRows();

    /**
     * Gets the number of columns in the current grid in order for the frontend to draw the grid
     * @return the number of columns
     */
    int getNumCols();

    /***** PLAYER METHODS *****/

    /**
     * Gets the row of the player
     * @return the row
     */
    int getPlayerRow();

    /**
     * Gets the column of the player
     * @return the column
     */
    int getPlayerColumn();

    /**
     * Gets the index of the grid that contains the player
     * @return the index
     */
    int getPlayerGridIndex();

    /**
     * Takes in a user input and calls the game instance class to process it. The frontend calls this method once a key
     * input is sent so that the backend can affect the player.
     * @param input - the user input
     */
    void keyListener(UserInstruction input);

    /**
     * Gets the interactions for the frontend to display
     * @return the list of interactions
     */
    List<BlockUpdate> getInteractions();


    /***** DATA METHODS *****/

    /**
     * Saves the status of a game by saving the grid world and player in a file
     * @param file - the myIconPath of the file that will contain the game
     */
    void saveEngine(String file);

    /**
     * Loads a game file containing a grid world and player
     * @param file - the myIconPath of the file that contains the game
     */
    void loadEngine(String file);
}
