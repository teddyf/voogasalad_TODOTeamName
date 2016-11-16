package engine;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game engine that is used to run games.
 *
 *         Dependencies: FileBrowser.java
 */
public class GameEngine extends Scene {

    private static final String ENGINE_RESOURCES = "resources/gameengine";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEngine(Stage stage, UILauncher launcher, Parent root) {
        super(root);
        myStage = stage;
        myLauncher = launcher;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
    }

    /**
     * Initializes the game engine window and prompts the user to choose a game
     * file to open for playing
     *
     * @return true if initialization was successful and a valid game file was chosen
     */
    public boolean init() {
        File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile == null) { // user clicked cancel
            return false;
        }
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
        return true;
    }


}
