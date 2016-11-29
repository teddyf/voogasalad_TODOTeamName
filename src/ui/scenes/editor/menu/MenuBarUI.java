package ui.scenes.editor.menu;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;


/**
 * @author Robert Steilberg
 *         <p>
 *         This class initializes the menu bar for the game editor.
 */
public class MenuBarUI {

    private Stage myStage;
    private Parent myRoot;
    private ResourceBundle myResources;
    private MenuBar myMenuBar;
    private UIBuilder myBuilder;
    private UILauncher myLauncher;

    public MenuBarUI(Stage stage, Parent root, UILauncher launcher, ResourceBundle resources) {
        myStage = stage;
        myRoot = root;
        myResources = resources;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
    }

    /**
     * Creates the menu bar at the top of the screen which encompasses basic
     * editor functionality (i.e. opening and saving game files)
     */
    public void initMenuBar() {
        myMenuBar = new MenuBar();
        myMenuBar.prefWidthProperty().bind(myStage.widthProperty());
        MenuEvents events = new MenuEvents(myStage, myLauncher, myResources);
        Menu firstMenu = new Menu(myResources.getString("firstMenu"));
        events.addFirstMenuEvents(firstMenu);
        Menu secondMenu = new Menu(myResources.getString("secondMenu"));
        events.addSecondMenuEvents(secondMenu);
        Menu thirdMenu = new Menu(myResources.getString("thirdMenu"));
        events.addThirdMenuEvents(thirdMenu);

        myMenuBar.getMenus().addAll(firstMenu, secondMenu, thirdMenu);
        myBuilder.addComponent(myRoot, myMenuBar);
    }

}
