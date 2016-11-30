package engine;

import xml.GridXMLHandler;

/**
 * This is the controller for the game engine. It allows the backend and frontend to talk to each other while the game
 * is being played.
 * @author Aninda Manocha
 */

public class EngineController {
    private GridXMLHandler xmlHandler;
    private GameInstance gameInstance;

    public EngineController() {
        xmlHandler = new GridXMLHandler();
        //gameInstance = new GameInstance();
    }
}
