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

    public EngineController() {
        xmlHandler = new GridXMLHandler();
//        gameInstance = new GameInstance(xmlHandler);
        gameInstances = new ArrayList<GameInstance>();
    }

    public void keyListener(UserInstruction input) {
        gameInstance.processInput(input);
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

    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        Player player = gridWorldAndPlayer.getPlayer();
        GridWorld gridWorld = gridWorldAndPlayer.getGridWorld();
//        gameInstance
//        changeGrid();

    }
}
