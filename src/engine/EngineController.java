package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import api.Controller;
import api.Player;
import block.BlockUpdate;
import grid.GridManager;
import player.PlayerDirection;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game engine. It allows the backend and frontend to talk to each other while the game
 * is being played.
 * @author Aninda Manocha, Nisakorn Valyasevi
 */

public class EngineController extends Observable implements Observer, Controller {
    private GridXMLHandler xmlHandler;
    private List<GameInstance> gameInstances;
    private GameInstance gameInstance;
    private int gameInstanceIndex;

    public EngineController() {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
    }

    public EngineController(Player player, GridManager gridManager, String musicFile) {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
        gameInstance = new GameInstance(player, gridManager);
        gameInstance.addObserver(this);
        gameInstance.setMusic(musicFile);
    }

    /* GRID METHODS */

    /**
     * Changes to a different specified grid, identified by its index in the list of grids
     * @param index - the index of the grid
     */
    public void changeGrid(int index) {
        gameInstance.changeGrid(index);
    }

    /**
     * Gets the block located in a specific row and column in the grid. The frontend calls this method in order to
     * render a grid block by block.
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return gameInstance.getGrid().getBlock(row, col).getName();
    }

    /**
     * Gets the interactions for the frontend to display
     * @return the list of interactions
     */
    public List<BlockUpdate> getInteractions() {
        return gameInstance.getInteractions();
    }

    /**
     * Gets the number of rows in the current grid in order for the frontend to draw the grid
     * @return the number of rows
     */
    public int getNumRows() {
        return gameInstance.getGrid().getNumRows();
    }

    /**
     * Gets the number of columns in the current grid in order for the frontend to draw the grid
     * @return the number of columns
     */
    public int getNumCols() {
        return gameInstance.getGrid().getNumCols();
    }

    /**
     * Gets the name of the music file for the game
     * @return the file name
     */
    public String getMusic() {
        return gameInstance.getMusic();
    }

    /* PLAYER METHODS */

    /**
     * Takes in a user input and calls the game instance class to process it. The frontend calls this method once a key
     * input is sent so that the backend can affect the player.
     * @param input - the user input
     */
    public void keyListener(UserInstruction input) {
        gameInstance.processInput(input);
    }

    public List<String> getPlayerImages() {
        return gameInstance.getPlayerNames();
    }

    public String getPlayerName() {
    	return gameInstance.getPlayerName();
    }

    public int getPlayerRow() {
        return gameInstance.getPlayerRow();
    }

    public int getPlayerColumn() {
        return gameInstance.getPlayerColumn();
    }

    public PlayerDirection getPlayerDirection() {
    	return gameInstance.getPlayerDirection();
    }

    public double getPlayerHealth() {
        return gameInstance.getPlayerHealth();
    }

    public int getPlayerNumPokemon() {
        return gameInstance.getPlayer().getNumPokemon();
    }

    /**
     * Passes a player update type to the frontend to update the display of the player when a player's information has
     * been changed (the game instance is observed in order to detect a change in the player's information)
     * @param observableValue - the observable game instance
     * @param value - the player update type
     */
    public void update(Observable observableValue, Object value) {
        if (observableValue instanceof GameInstance) {
            setChanged();
            notifyObservers(value);
        }
    }

    /* DATA METHODS */

    /**
     * Saves the status of a game by saving the grid world and player in a file
     * @param file - the myIconPath of the file that will contain the game
     */
    public void saveEngine(String file) {
        gameInstance.saveEngine(file);
    }

    /**
     * Loads a game file containing a grid world and player
     * @param file - the myIconPath of the file that contains the game
     */
    public void loadEngine(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridManager gridManager = new GridManager(gridWorldAndPlayer.getGridWorld().getGrids());
        gridManager.addMusic(gridWorldAndPlayer.getGridWorld().getMusicFile());
        gameInstance = new GameInstance(player, gridManager);
        gameInstance.addObserver(this);
    }
}
