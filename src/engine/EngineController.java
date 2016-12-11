package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import block.BlockUpdate;
import grid.GridWorld;
import player.Player;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game engine. It allows the backend and frontend to talk to each other while the game
 * is being played.
 * @author Aninda Manocha
 */

public class EngineController extends Observable implements Observer {
    private GridXMLHandler xmlHandler;
    private GameInstance gameInstance;
    private List<GameInstance> gameInstances;
    private int gameInstanceIndex;

    public EngineController() {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
    }

    public EngineController(Player player, GridWorld gridWorld) {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
        gameInstance = new GameInstance(player, gridWorld);
        gameInstance.addObserver(this);
    }

    /**
     * Takes in a user input and calls the game instance class to process it. The frontend calls this method once a key
     * input is sent so that the backend can affect the player.
     * @param input - the user input
     */
    public void keyListener(UserInstruction input) {
        gameInstance.processInput(input);
    }

    public List<BlockUpdate> getInteractions() {
        return gameInstance.getBlockUpdates();
    }

    public void changeGrid(int index) {
        gameInstance.changeGrid(index);
    }

    public String getBlock(int row, int col) {
        return gameInstance.getGrid().getBlock(row, col).getName();
    }

    public List<BlockUpdate> getBlockUpdates() { //what the frontend calls when it receives interaction update
        return gameInstance.getBlockUpdates();
    }

    public int getPlayerRow() {
        return gameInstance.getPlayer().getRow();
    }

    public int getPlayerColumn() {
        return gameInstance.getPlayer().getCol();
    }

    public int getPlayerGridIndex() {
        return gameInstance.getPlayer().getGridIndex();
    }

    public GameInstance getGameInstance() {
		return gameInstance;
	}

    /**
     * Saves the status of a game by saving the grid world and player in a file
     * @param file - the myIconPath of the file that will contain the game
     */
    public void saveEngine(String file) {
        xmlHandler.saveContents(file, gameInstance.getGridWorld(), gameInstance.getPlayer());
    }

    /**
     * Loads a game file containing a grid world and player
     * @param file - the myIconPath of the file that contains the game
     */
    public void loadEngine(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridWorld gridWorld = gridWorldAndPlayer.getGridWorld();
        gameInstance = new GameInstance(player, gridWorld);
        gameInstance.addObserver(this);
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
}
