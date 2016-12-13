package api;

import block.BlockUpdate;
import engine.UserInstruction;
import engine.GameStatus;
import grid.Grid;
import player.PlayerInstance;

import java.util.List;

/**
 * The game instance interface
 * @author Aninda Manocha
 */

public interface IGameInstance {

    /**
     * Changes to a different specified grid, identified by its index in the list of grids
     * @param index - the index of the grid
     */
    void changeGrid(int index);

	/**
	 * Processes the user input and moves the player based on the input
	 * @param input - the user input
	 */
	void processInput(UserInstruction input);

    /**
     * Gets the interactions for the frontend to display
     * @return the list of interactions
     */
    List<BlockUpdate> getInteractions();

    /**
     * Gets the current grid that the player is located in
     * @return the current grid
     */
    Grid getGrid();

	/**
	 * Gets the player character
	 * @return the player character
	 */
	PlayerInstance getPlayer();

	/**
	 * Gets the score of the game
	 * @return the game
	 */
	int getScore();

	/**
	 * Gets the status of the game
	 * @return the status
	 */
	GameStatus getGameStatus();
}
