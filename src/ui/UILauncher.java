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
import java.util.*;

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
    private Scene prevScene;
    EditorController myController;
    Stack<Scene> st;
    Stack<String> pathSt;

    public UILauncher(Stage stage) {
        myStage = stage;
        myStage.setResizable(false);
        myController = new EditorController();
        st = new Stack<Scene>();
        pathSt = new Stack<String>();
    }

    /**
     * Navigates to the game engine
     */
    public void launchEngine() {
        GameEngine engine = new GameEngine(myStage, new Group(), this);
        prevScene = engine;
        st.add(engine);
        pathSt.add(engine.getPath());
        if (engine.init()) { // successfully opened a game file
            myStage.setScene(engine);
        }
    }

    /**
     * Navigates to the game editor
     */
    public void launchEditor() {
        GameEditor editor = new GameEditor(myStage, new Group(), this);
        prevScene = editor;
        pathSt.add(editor.getPath());
        st.add(editor);
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
        CharacterEditor charEdit = new CharacterEditor(myStage, new Group(), this, myController);
        prevScene = charEdit;
        st.add(prevScene);
        pathSt.add(charEdit.getPath());
        myStage.setScene(charEdit);
    }
    
    public void launchAttributePopup(){
        AttributeEditor attEdit = new AttributeEditor(myStage, new Group(),this, myController);
        //prevScene = attEdit;
        st.add(attEdit);
        pathSt.add(attEdit.getPath());
        myStage.setScene(attEdit);
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game engine.
     */
    public void init() {
        launchMenu();
    }
    
    public void goToPrevScene(UIBuilder builder){
        st.pop();
        pathSt.pop();
        String path = pathSt.peek();
        myStage.setScene(st.peek());
        builder.initWindow(myStage,path);
    }
    
}
