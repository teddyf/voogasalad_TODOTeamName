package ui.scenes.editor;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;
import ui.scenes.editor.menu.MenuBarUI;
import ui.scenes.editor.menu.MenuEvents;
import java.util.ResourceBundle;


/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, Parent root, UILauncher launcher) {
        super(root,Color.web("#0585B2"));
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            e.consume();
            MenuEvents events = new MenuEvents(myStage,myLauncher,myResources);
            events.newVOOGA();
        });
    }

    protected void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        MenuBarUI menuBar = new MenuBarUI(myStage,myRoot,myLauncher,myResources);
        menuBar.initMenuBar();
        ItemMenuUI itemMenu = new ItemMenuUI(myRoot, myBuilder, myResources);
        PlayerMenuUI playerMenu = new PlayerMenuUI(myRoot, myBuilder, myResources);
        playerMenu.initPlayerMenu();
        GridUI grid = new GridUI(myRoot, itemMenu.initItemMenu(), myResources);
        grid.initGrid(width,height);
        myStage.setScene(this);
    }

    /**
     * Initializes the game editor window
     */
    public void initEditor() {
        // use alert and get W and H
        SizeChooserUI sizeChooser = new SizeChooserUI(myStage,new Group(),this,myBuilder);
        sizeChooser.promptUserForSize();
    }
}
