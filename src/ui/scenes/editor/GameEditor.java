package ui.scenes.editor;

import editor.EditorController;
import engine.EngineController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
    private static final String CSS_FILE_NAME = "resources/styles/game-editor.css";
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, Parent root, UILauncher launcher) {
        super(root, Color.web("#0585B2"));
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
    }

    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        ItemMenuUI itemMenu = new ItemMenuUI(myRoot, myBuilder, myResources);
        EditorController editorController = new EditorController();
        GridUI grid = new GridUI(myRoot, itemMenu.initItemMenu(), editorController, myResources);
        grid.initGrid(width, height);
//        PlayerMenuUI playerMenu = new PlayerMenuUI(myRoot, myBuilder, myResources, editorController);
//        playerMenu.initPlayerMenu();
        EditorIO IO = new EditorIO(myStage, editorController, new EngineController(), myResources, grid);
        EditorEvents events = new EditorEvents(myLauncher, IO, myResources);
        MenuBarUI menuBar = new MenuBarUI(myStage, myRoot, events, myResources);
        menuBar.initMenuBar();
//        EngineController loadedEngine = editorController.runEngine(); // running test
        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            e.consume();
            events.exitPrompt(false);
        });
        myStage.setScene(this);
    }

    /**
     * Initializes the game editor window by prompting the user to choose an initial
     * overworld size
     */
    public void initEditor() {
        SizeChooserUI sizeChooser = new SizeChooserUI(myStage, new Group(), this, myLauncher, myBuilder);
        sizeChooser.promptUserForSize();
    }
}
