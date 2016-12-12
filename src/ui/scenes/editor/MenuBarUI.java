package ui.scenes.editor;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;


/**
 * @author Robert Steilberg
 *         <p>
 *         This class initializes the menu bar for the game editor.
 */
class MenuBarUI {

    private Stage myStage;
    private Parent myRoot;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;

    private EditorEvents myEditorEvents;

    MenuBarUI(Stage stage, Parent root, EditorEvents editorEvents, ResourceBundle resources) {
        myStage = stage;
        myRoot = root;
        myEditorEvents = editorEvents;
        myResources = resources;
        myBuilder = new UIBuilder();
        init();
    }

    /**
     * Creates the menu bar at the top of the screen which encompasses basic
     * editor functionality (i.e. opening and saving game files)
     */
    private void init() {
        MenuBar menu = new MenuBar();
        menu.prefWidthProperty().bind(myStage.widthProperty());
        Menu firstMenu = new Menu(myResources.getString("firstMenu"));
        myEditorEvents.addFirstMenuEvents(firstMenu);
//        Menu secondMenu = new Menu(myResources.getString("secondMenu"));
//        myEditorEvents.addSecondMenuEvents(secondMenu);
        Menu thirdMenu = new Menu(myResources.getString("thirdMenu"));
        myEditorEvents.addThirdMenuEvents(thirdMenu);
        menu.getMenus().addAll(firstMenu, thirdMenu);
        myBuilder.addComponent(myRoot, menu);
    }

}
