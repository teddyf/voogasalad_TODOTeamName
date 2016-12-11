package ui;

import ui.scenes.editor.EditorView;
import ui.scenes.engine.EngineView;
import ui.scenes.MainMenu;
import editor.EditorController;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         Launches the main menu, which is used to navigate to either the game
 *         builder or the game engine.
 *         <p>
 *         Dependencies: MainMenu.java
 */
public class UILauncher {

    private Stage myStage;
    private MainMenu myMainMenu;
    private EditorController myController;

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
        myController = new EditorController();
    }

    /**
     * Navigates to the game engine
     */
    public void launchEngine() {
        EngineView engine = new EngineView(myStage, new Group(), this);
        if (engine.init()) { // successfully opened a game file
            myStage.setScene(engine);
        }
    }

    /**
     * Navigates to the game editor
     */
    public void launchEditor() {
        EditorView editor = new EditorView(myStage, new Group(), this, myController);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
           e.consume();
           launchMenu();
        });
        editor.initEditor();
    }

    /**
     * Navigates to the main menu
     */
    public void launchMenu() {
        myMainMenu = new MainMenu(myStage, new Group(), this);
        myStage.setScene(myMainMenu);
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game engine.
     */
    public void init() {
        launchMenu();
    }

    
}
