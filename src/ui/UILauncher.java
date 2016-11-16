package ui;

import editor.GameEditor;
import menu.MainMenu;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg
 *
 * Launches the main menu, which is used to navigate to either the game
 * builder or the game engine.
 *
 * Dependencies: MainMenu.java
 */
public class UILauncher {

    private static final String MAINMENU_RESOURCES = "resources/mainmenu";
    private Stage myStage;
    private ResourceBundle myResources;
	private MainMenu myMainMenu;
	
	public UILauncher(Stage stage) {
		myStage = stage;
        myResources = ResourceBundle.getBundle(MAINMENU_RESOURCES);
	}

	public void launchEditor() {
        GameEditor editor = new GameEditor(new Group());
        myStage.setScene(editor);
    }

    /**
     * Navigates to the main menu
     */
	public void launchMenu() {
		myStage.setScene(myMainMenu);
	}

    /**
     * Initializes the basic properties of the main menu window.
     */
    private void initWindow() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setResizable(false);
        myStage.centerOnScreen();
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.show();
    }

    /**
     * Launches the main menu that progresses into
     * either the game builder or game engine.
     */
	public void init() {
        initWindow();
        myMainMenu = new MainMenu(this, new Group());
        launchMenu();
    }
}
