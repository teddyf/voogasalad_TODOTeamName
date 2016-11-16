package ui;

import editor.GameEditor;
import engine.GameEngine;
import menu.MainMenu;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.util.ResourceBundle;

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

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
        myStage.setOnCloseRequest(e -> {
            e.consume();
            launchMenu();
        });
    }

    public void launchEngine() {
        GameEngine engine = new GameEngine(myStage, new Group());
        myStage.setScene(engine);
    }

    public void launchEditor() {
        GameEditor editor = new GameEditor(myStage, new Group());
        myStage.setScene(editor);
    }

    /**
     * Navigates to the main menu
     */
    public void launchMenu() {
        myMainMenu = new MainMenu(myStage, this, new Group());
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
