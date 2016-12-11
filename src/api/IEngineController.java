package api;

import block.BlockUpdate;
import engine.GameInstance;
import engine.UserInstruction;
import grid.GridWorld;
import player.Player;
import xml.GridWorldAndPlayer;

import java.util.List;
import java.util.Observable;

/**
 * The engine controller interface
 * @author Aninda Manocha
 */

public interface IEngineController extends IController {

    /**
     * Takes in a user input and calls the game instance class to process it. The frontend calls this method once a key
     * input is sent so that the backend can affect the player.
     * @param input - the user input
     */
    public void keyListener(UserInstruction input);

    public List<BlockUpdate> getInteractions();

    GameInstance getGameInstance();

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
     * Passes a player update type to the frontend to update the display of the player when a player's information has
     * been changed (the game instance is observed in order to detect a change in the player's information)
     * @param observableValue - the observable game instance
     * @param value - the player update type
     */
    void update(Observable observableValue, Object value);

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
