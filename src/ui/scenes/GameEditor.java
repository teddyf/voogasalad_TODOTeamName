package ui.scenes;

import editor.Screen;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *
 * This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/properties/gameeditor";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, UILauncher launcher, Parent root) {
        super(root);
        myStage = stage;
        myLauncher = launcher;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        initEditor();
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
    }

    /**
     * Initializes the game editor window
     */
    private void initEditor() {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        myBuilder.addComponent(myRoot,new Screen(Integer.parseInt(myResources.getString("screenWidth")),Integer.parseInt(myResources.getString("screenHeight"))).getRoot());
    }
}