package ui;

import ui.scenes.editor.GameEditor;
import ui.scenes.engine.GameEngine;
import ui.scenes.CharacterEditor;
import ui.scenes.MainMenu;
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

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
    }

    /**
     * Navigates to the game engine
     */
    public void launchEngine() {
        GameEngine engine = new GameEngine(myStage, new Group(), this);
        if (engine.init()) { // successfully opened a game file
            myStage.setScene(engine);
        }
    }

    /**
     * Navigates to the game editor
     */
    public void launchEditor() {
        GameEditor editor = new GameEditor(myStage, new Group(), this);
        editor.initEditor();
    }

    /**
     * Navigates to the main menu
     */
    public void launchMenu() {
        myMainMenu = new MainMenu(myStage, new Group(), this);
        myStage.setScene(myMainMenu);
    }

    public void launchCharacterMenu(){
        CharacterEditor charEdit = new CharacterEditor(myStage, new Group(), this);
        myStage.setScene(charEdit);
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game engine.
     */
    public void init() {
        launchMenu();
    }
}
