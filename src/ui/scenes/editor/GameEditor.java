package ui.scenes.editor;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;
import ui.GridPaneNode;
import java.util.ResourceBundle;


/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/properties/gameeditor";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, UILauncher launcher, Parent root) {
        super(root,Color.web("#4B84AF"));
        myStage = stage;
        myLauncher = launcher;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
    }

    /**
     * Initializes the game editor window
     */
    public void initEditor() {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        MenuBarUI menuBar = new MenuBarUI(myStage,myRoot,myLauncher,EDITOR_RESOURCES);
        menuBar.initMenuBar();

        ItemMenuUI itemMenu = new ItemMenuUI(myRoot, myBuilder, EDITOR_RESOURCES);
        GridUI grid = new GridUI(myRoot, itemMenu.initItemMenu(), EDITOR_RESOURCES);
        grid.initGrid();
    }
}
