package ui.scenes.editor;

import editor.Screen;
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
 *
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
//        myGridPane =
//                new GridPane(Integer.parseInt(myResources.getString("gridCellsWide")),
//                             Integer.parseInt(myResources.getString("gridCellsHeight")),
//                             Integer.parseInt(myResources.getString("gridWidth")),
//                             Integer.parseInt(myResources.getString("gridHeight")),
//                             Integer.parseInt(myResources.getString("gridX")),
//                             Integer.parseInt(myResources.getString("gridY")));
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
        initEditor();



    }
    /**
     * Initializes the game editor window
     */
    private void initEditor () {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        GridUI grid = new GridUI(myRoot);
        grid.initGrid();

//        myBuilder.addComponent(myRoot,
//                               new Screen(Integer.parseInt(myResources.getString("screenWidth")),
//                                          Integer.parseInt(myResources.getString("screenHeight")))
//                                                  .getRoot());

    }



}
