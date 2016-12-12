package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import api.IEngineController;
import block.BlockUpdate;
import grid.GridManager;
import grid.GridWorld;
import player.Player;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game engine. It allows the backend and frontend to talk to each other while the game
 * is being played.
 * @author Aninda Manocha
 */

public class EngineController extends Observable implements Observer, IEngineController, Cloneable {
    private GridXMLHandler xmlHandler;
    private List<GameInstance> gameInstances;
    private GameInstance gameInstance;
    private int gameInstanceIndex;

    public EngineController() {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
    }

    public EngineController(Player player, GridManager gridManager) {
        xmlHandler = new GridXMLHandler();
        gameInstances = new ArrayList<GameInstance>();
        gameInstance = new GameInstance(player, gridManager);
        //System.out.println(gameInstance);
        gameInstance.addObserver(this);
    }

    /***** GRID METHODS *****/

    public int getNumRows() {
        return gameInstance.getGrid().getNumRows();
    }

    public int getNumCols() {
        return gameInstance.getGrid().getNumCols();
    }

    public void changeGrid(int index) {
        gameInstance.changeGrid(index);
    }

    public String getBlock(int row, int col) {
        return gameInstance.getGrid().getBlock(row, col).getName();
    }

    public String getMusic() {
        return gameInstance.getMusic();
    }

    /***** PLAYER METHODS *****/

    public int getPlayerRow() {
        return gameInstance.getPlayer().getRow();
    }

    public int getPlayerColumn() {
        return gameInstance.getPlayer().getCol();
    }

    public int getPlayerGridIndex() {
        return gameInstance.getPlayer().getGridIndex();
    }

    public List<String> getPlayerImages() {
        return gameInstance.getPlayer().getMyNames();
    }

    public void keyListener(UserInstruction input) {
        gameInstance.processInput(input);
    }

    public List<BlockUpdate> getInteractions() {
        return gameInstance.getInteractions();
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

    /***** DATA METHODS *****/

    public void saveEngine(String file) {
        gameInstance.saveEngine(file);
    }

    public void loadEngine(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridManager gridManager = new GridManager(gridWorldAndPlayer.getGridWorld().getGrids());
        gridManager.setMusic(gridWorldAndPlayer.getGridWorld().getMusicFile());
        gameInstance = new GameInstance(player, gridManager);
        gameInstance.addObserver(this);
    }
}
