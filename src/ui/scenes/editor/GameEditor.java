package ui.scenes.editor;

import grid.*;
import editor.SidePanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

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
    private Group mySideMenuRegion;
    private ResourceBundle myResources;
    private GridPane myGridPane;
    private SidePanel sideMenu;

    public GameEditor(Stage stage, UILauncher launcher, Parent root) {
        super(root);
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
        ItemMenuUI itemMenu = new ItemMenuUI(myRoot, myBuilder, EDITOR_RESOURCES);

        GridUI grid = new GridUI(myRoot, itemMenu.initItemMenu(), EDITOR_RESOURCES);
        grid.initGrid();
    }
}
