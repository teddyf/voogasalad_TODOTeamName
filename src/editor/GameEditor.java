package editor;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *
 * This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/gameeditor";
    private Stage myStage;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, Parent root) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        initEditor();
    }

    /**
     * Initializes the game editor window
     */
    private void initEditor() {
        myBuilder.initWindow(myStage,EDITOR_RESOURCES);
    }


}
