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
    }

    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);

        ItemMenuUI itemMenu = new ItemMenuUI(myRoot, myBuilder, myResources);

        EditorController editorController = new EditorController();
        //PlayerMenuUI playerMenu = new PlayerMenuUI(myRoot, myBuilder, myResources,editorController);
        //playerMenu.initPlayerMenu();
        GridUI grid = new GridUI(myRoot, itemMenu.initItemMenu(), editorController, myResources);
        grid.initGrid(width,height);

        EditorIO IO = new EditorIO(myStage,editorController,new EngineController(),myResources);

        MenuBarUI menuBar = new MenuBarUI(myStage,myRoot,myLauncher,IO,myResources);
        menuBar.initMenuBar();

//        editorController.saveEngine("f") // exporting finished game
//        EngineController loadedEngine = editorController.runEngine(); // running test

        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            e.consume();
            new MenuEvents(myStage,myLauncher,IO,myResources).exitPrompt();
        });
        myStage.setScene(this);
    }

    /**
     * Initializes the game editor window
     */
    public void initEditor() {
        SizeChooserUI sizeChooser = new SizeChooserUI(myStage,new Group(),this,myLauncher,myBuilder);
        sizeChooser.promptUserForSize();
    }
}
