package ui.scenes;
import ui.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game engine that is used to run games.
 *
 *         Dependencies: FileBrowser.java
 */
public class GameEngine extends Scene {

    private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
	private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private GridPane gridPane;

    public GameEngine(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
    }

    /**
     * Initializes the game engine window and prompts the user to choose a game
     * file to open for playing
     *
     * @return true if initialization was successful and a valid game file was chosen
     */
    public boolean init() {
        /*File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile == null) { // user clicked cancel
            return false;
        }*/
    	
    	setUpPane();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
        return true;
    }
    
    private void setUpPane() {
    	Pane pane = new Pane();
    	myBuilder.addComponent(myRoot, pane);
    	Character character = new Character(this);
		pane.getChildren().add(character.getCharacterImageView());
    }
}
