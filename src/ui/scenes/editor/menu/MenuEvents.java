package ui.scenes.editor.menu;

import javafx.scene.control.*;
import javafx.stage.Stage;
import ui.FileBrowser;
import ui.UILauncher;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class MenuEvents {

    private Stage myStage;
    private UILauncher myLauncher;
    private ResourceBundle myResources;

    MenuEvents(Stage stage, UILauncher launcher, ResourceBundle resources) {
        myStage = stage;
        myLauncher = launcher;
        myResources = resources;

    }

    private File saveGameFile() {
        File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            return gameFile;
        }
        return null;
    }

    private File openGameFile() {
        File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) { // user clicked cancel
            return gameFile;
        }
        return null;
    }

    private void newVOOGA() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to save the changes made to the current game?");
        alert.setContentText("Your changes will be lost if you don't save them.");
        ButtonType save = new ButtonType("Save");
        ButtonType noSave = new ButtonType("Don't Save");
        ButtonType cancel = new ButtonType("Cancexl");
        alert.getButtonTypes().setAll(cancel,noSave,save);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == save){
            File savedFile = saveGameFile();
            if (savedFile != null) {
                // save file
                myLauncher.launchMenu();
            }
        } else if (result.get() == noSave) {
            myLauncher.launchMenu();
        }
    }

    public void addThirdMenuEvents(Menu thirdMenu) {

    }

    public void addSecondMenuEvents(Menu secondMenu) {
        MenuItem itemUndo = new MenuItem("Undo");
//        itemUndo.setOnAction(e -> undo());
        secondMenu.getItems().add(itemUndo);

        MenuItem itemRedo = new MenuItem("Redo");
//        itemUndo.setOnAction(e -> redo());
        secondMenu.getItems().add(itemRedo);

        MenuItem itemCopy = new MenuItem("Copy");
//        itemUndo.setOnAction(e -> copy());
        secondMenu.getItems().add(itemCopy);

        MenuItem itemPaste = new MenuItem("Paste");
//        itemUndo.setOnAction(e -> redo());
        secondMenu.getItems().add(itemPaste);

        MenuItem itemDelete = new MenuItem("Delete");
//        itemUndo.setOnAction(e -> redo());
        secondMenu.getItems().add(itemDelete);
    }

    public void addFirstMenuEvents(Menu firstMenu) {

        MenuItem itemNew = new MenuItem("New");
        itemNew.setOnAction(e -> newVOOGA());
        firstMenu.getItems().add(itemNew);

        MenuItem itemOpen = new MenuItem("Open");
        itemOpen.setOnAction(e -> openGameFile());
        firstMenu.getItems().add(itemOpen);

        MenuItem itemSave = new MenuItem("Save");
        itemSave.setOnAction(e -> saveGameFile());
        firstMenu.getItems().add(itemSave);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnAction(e -> myLauncher.launchMenu());
        firstMenu.getItems().add(itemExit);

    }

}
