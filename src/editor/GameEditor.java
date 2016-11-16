package editor;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
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

    private void initEditor() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();
    }


}
