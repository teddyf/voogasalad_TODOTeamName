package ui;

import ui.scenes.editor.GameEditor;
import ui.scenes.engine.GameEngine;
import ui.builder.UIBuilder;
import ui.scenes.AttributeEditor;
import ui.scenes.CharacterEditor;
import ui.scenes.MainMenu;
import editor.EditorController;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    EditorController myController;
    private Scene prevEditor;
    private Scene prevCharEditor;

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
        myController = new EditorController();
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
        GameEditor editor = new GameEditor(myStage, new Group(), this, myController);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
           e.consume();
           launchMenu();
        });
        editor.initEditor();
        prevEditor = editor;
    }

    /**
     * Navigates to the main menu
     */
    public void launchMenu() {
        myMainMenu = new MainMenu(myStage, new Group(), this);
        myStage.setScene(myMainMenu);
    }

    public void launchCharacterMenu(){
        CharacterEditor charEdit = new CharacterEditor(myStage, new Group(), this, myController);
        myStage.setScene(charEdit);
        prevCharEditor = charEdit;
    }
    
    public void launchAttributePopup(){
        AttributeEditor attEdit = new AttributeEditor(myStage, new Group(),this, myController);
        //prevScene = attEdit;
        myStage.setScene(attEdit);
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game engine.
     */
    public void init() {
        launchMenu();
    }
    
    public void goToPrevEditor(UIBuilder builder){  
        myStage.setScene(prevEditor);
        String path = ((GameEditor) prevEditor).getPath();
        builder.initWindow(myStage,path);
    }
    
    public void goToPrevCharEditor(UIBuilder builder){
        myStage.setScene(prevCharEditor);
        String path = ((CharacterEditor) prevCharEditor).getPath();
        builder.initWindow(myStage,path);
    }
    
}
