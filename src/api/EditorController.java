package api;

import block.blocktypes.BlockType;
import engine.EngineController;
import grid.GridSizeDirection;

import java.util.List;

/**
 * The editor controller interface
 * @author Aninda Manocha
 */

public interface EditorController extends Controller {

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
