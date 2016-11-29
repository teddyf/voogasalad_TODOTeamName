package ui.scenes.editor;

import ui.FileBrowser;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.io.File;
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

    MenuBarUI(Stage stage, Parent root, UILauncher launcher, String resourcesPath) {
        myStage = stage;
        myRoot = root;
        myResources = ResourceBundle.getBundle(resourcesPath);
        myLauncher = launcher;
        myBuilder = new UIBuilder();
    }

    private void addFirstMenuEvents(Menu firstMenu) {

        MenuItem itemNew = new MenuItem("New");
        itemNew.setOnAction(e -> myLauncher.launchEditor());
        firstMenu.getItems().add(itemNew);

        MenuItem itemOpen = new MenuItem("Open");
        itemOpen.setOnAction(e -> {
            File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
            if (gameFile != null) { // user clicked cancel
                // open game
            }
        });
        firstMenu.getItems().add(itemOpen);

        MenuItem itemSave = new MenuItem("Save");
        itemSave.setOnAction(e -> {
            File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
            if (gameFile != null) { // user clicked cancel
                // save game
            }
        });
        firstMenu.getItems().add(itemSave);

//        MenuItem itemExport = new MenuItem("Export to Game");
//        itemExport.setOnAction(e -> myLauncher.launchEditor());
//        firstMenu.getItems().add(itemExport);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnAction(e -> myLauncher.launchMenu());
        firstMenu.getItems().add(itemExit);
    }

    private void createMenuBar() {
        myMenuBar = new MenuBar();
        myMenuBar.prefWidthProperty().bind(myStage.widthProperty());
        Menu firstMenu = new Menu(myResources.getString("firstMenu"));
        addFirstMenuEvents(firstMenu);



        myMenuBar.getMenus().addAll(firstMenu);
        myBuilder.addComponent(myRoot, myMenuBar);
    }

    public void initMenuBar() {
        createMenuBar();
    }

}
