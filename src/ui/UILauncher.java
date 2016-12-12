package ui;

import ui.scenes.MainMenuView;
import ui.scenes.WinSceneView;
import ui.scenes.editor.EditorView;
import ui.scenes.engine.EngineView;
import editor.EditorController;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         Launches the main menu, which is used to navigate to either the game
 *         builder or the game engine.
 *         <p>
 *         Dependencies: MainMenuView.java
 */
public class UILauncher {

    private Stage myStage;
    private MainMenuView myMainMenu;
    private EngineView myEngine;

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
    }

    public void launchWinScene() {
        WinSceneView winScene = new WinSceneView(myStage, new Group(), this, myEngine);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            launchMenu();
        });
        myStage.setScene(winScene);
    }

    /**
     * Navigates to the game engine
     */
    public void launchEngine() {
        myEngine = new EngineView(myStage, new Group(), this);
        if (myEngine.init(false)) { // successfully opened a game file
            myStage.setScene(myEngine);
        }
    }

    /**
     * Navigates to the game editor
     */
    public void launchEditor() {
        EditorView editor = new EditorView(myStage, new Group(), this, new EditorController());
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            launchMenu();
        });
        editor.init();
    }

    /**
     * Navigates to the main menu
     */
    public void launchMenu() {
        myMainMenu = new MainMenuView(myStage, new Group(), this);
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
