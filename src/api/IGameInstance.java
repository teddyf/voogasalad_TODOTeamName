package api;

import engine.UserInstruction;
import engine.GameStatus;
import grid.Grid;
import player.Player;

/**
 * The game instance interface
 * @author Aninda Manocha
 */

public interface IGameInstance {

	/**
	 * Gets the player character
	 * @return the player character
	 */
	public Player getPlayer();

	/**
	 * Gets the current grid that the player is located in
	 * @return the current grid
	 */
	public Grid getGrid();

	/**
	 * Gets the score of the game
	 * @return the game
	 */
	public int getScore();

	/**
	 * Gets the status of the game
	 * @return the status
	 */
	public GameStatus getGameStatus();

	/**
	 * Processes the user input and moves the player based on the input
	 * @param input - the user input
     * @return the user input (to be sent back to the frontend)
	 */
	public UserInstruction processInput(UserInstruction input);
}
