package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    }

    /**
     * Takes in a user input and calls the game instance class to process it. The frontend calls this method once a key
     * input is sent.
     * @param input - the user input
     */
    public void keyListener(UserInstruction input) {
        gameInstance.processInput(input);
    }

    /**
     * Gets the block located in a specific row and column in the grid. The frontend calls this method in order to
     * render a grid block by block.
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return gameInstance.getRenderedGrid().get(row, col);
    }

    public void saveEngine(String file) {
        xmlHandler.saveContents(file, gameInstance.getGridWorld(), gameInstance.getPlayer());
    }

    public GameInstance getGameInstance() {
		return gameInstance;
	}
	

    public void loadEngine(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridWorld gridWorld = gridWorldAndPlayer.getGridWorld();
        gameInstance = new GameInstance(player, gridWorld);
    }
    
    public void addObserver(Observer observer) {
    	gameInstance.addObserver(observer);
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
