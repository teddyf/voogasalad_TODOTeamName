package engine;

import java.util.ArrayList;
import java.util.List;

import grid.GridWorld;
import player.Player;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game engine. It allows the backend and frontend to talk to each other while the game
 * is being played.
 * @author Aninda Manocha
 */

public class EngineController {
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

    public void keyListener(UserInstruction input) {
    	if (gameInstance == null)System.out.println("hi");
        gameInstance.processInput(input);
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    /**
     * Gets the block located in a specific row and column
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return gameInstance.getRenderedGrid().get(row, col);
    }

    public int getPlayerRow() {
        return gameInstance.getPlayer().getRow();
    }

    public int getPlayerColumn() {
        return gameInstance.getPlayer().getCol();
    }

    public void saveEngine(String file) {
        xmlHandler.saveContents(file, gameInstance.getGridWorld(), gameInstance.getPlayer());
    }

    public void loadEngine(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridWorld gridWorld = gridWorldAndPlayer.getGridWorld();
        gameInstance = new GameInstance(player, gridWorld);
    }
}
