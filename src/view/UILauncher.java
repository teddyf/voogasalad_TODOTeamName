package view;

import view.scenes.MainMenuView;
import view.scenes.WinSceneView;
import view.scenes.editor.EditorView;
import view.scenes.engine.EngineView;
import controller.editor.EditorController;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class can be used to launch any view
 */
public class UILauncher {

    private Stage myStage;
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
     * Navigates to the game controller.engine
     */
    public void launchEngine() {
        myEngine = new EngineView(myStage, new Group(), this);
        if (myEngine.init(false)) { // successfully opened a game file
            myStage.setScene(myEngine);
        }
    }

    /**
     * Navigates to the game controller.editor
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
        MainMenuView mainMenu = new MainMenuView(myStage, new Group(), this);
        myStage.setScene(mainMenu);
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game controller.engine.
     */
    public void init() {
        launchMenu();
    }
}