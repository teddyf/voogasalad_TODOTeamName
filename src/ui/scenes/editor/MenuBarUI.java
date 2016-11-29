package ui.scenes.editor;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import ui.builder.UIBuilder;


/**
 * @author Robert Steilberg
 *         <p>
 *         This class initializes the menu bar for the game editor.
 */
public class MenuBarUI {

    private Stage myStage;
    private Parent myRoot;
    private MenuBar myMenuBar;
    private UIBuilder myBuilder;

    MenuBarUI(Stage stage, Parent root, String resourcesPath) {
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
    }

    private void createMenuBar() {
        myMenuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        myMenuBar.getMenus().addAll(menuFile,menuEdit,menuView);
        myMenuBar.prefWidthProperty().bind(myStage.widthProperty());
        myBuilder.addComponent(myRoot,myMenuBar);
    }

    public void initMenuBar() {
        createMenuBar();
    }

}
