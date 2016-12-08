package ui.scenes.editor;

import javafx.scene.control.*;
import ui.UILauncher;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class adds items to each of the editor menus and configures their actions.
 */
public class EditorEvents {

    private UILauncher myLauncher;
    private EditorIO myIO;
    private ResourceBundle myResources;


    public EditorEvents(UILauncher launcher, EditorIO IO, ResourceBundle resources) {
        myLauncher = launcher;
        myIO = IO;
        myResources = resources;
    }


    /**
     * Prompts a user to save their current editor state on attempted exit from
     * the editor
     *
     * @param newEditor is true if a new editor instance should be created, false
     *                  if returning to the main menu
     */
    public void exitPrompt(boolean newEditor) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(myResources.getString("exitPromptHeader"));
        alert.setContentText(myResources.getString("exitPromptContent"));
        ButtonType save = new ButtonType(myResources.getString("saveButtonText"));
        ButtonType noSave = new ButtonType(myResources.getString("noSaveButtonText"));
        ButtonType cancel = new ButtonType(myResources.getString("cancelButtonText"));
        alert.getButtonTypes().setAll(cancel, noSave, save);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == save) {
            boolean savedFile = myIO.saveEditorFile();
            if (savedFile) {
                myLauncher.launchMenu();
            }
        } else if (result.get() == noSave) {
            if (newEditor) {
                myLauncher.launchEditor();
            } else {
                myLauncher.launchMenu();
            }
        }
    }

    /**
     * Adds sub-items and their actions to the third menu of the
     * screen.
     * <p>
     * Note: Styles for the Menu are set here rather than in CSS because CSS
     * only supports the :hover selector when the mouse is directly hovering
     * over the object.
     *
     * @param thirdMenu is the menu to which events are added
     */
    public void addThirdMenuEvents(Menu thirdMenu) {
        thirdMenu.setOnShowing(e -> thirdMenu.setStyle("-fx-background-color: " + myResources.getString("menuColor")));
        thirdMenu.setOnHidden(e -> thirdMenu.setStyle("-fx-background-color: " + myResources.getString("menuHoverColor")));
        // create test game menu option
        MenuItem itemTest = new MenuItem("Run Game");
        itemTest.setOnAction(e -> myIO.runGameInEditor());
        // create export game file menu option
        MenuItem itemExport = new MenuItem("Export as Game");
        itemExport.setOnAction(e -> myIO.saveGameFile());
        thirdMenu.getItems().addAll(itemTest, itemExport);
    }

    /**
     * Adds sub-items and their actions to the second menu of the
     * screen.
     * <p>
     * Note: Styles for the Menu are set here rather than in CSS because CSS
     * only supports the :hover selector when the mouse is directly hovering
     * over the object.
     *
     * @param secondMenu is the menu to which events are added
     */
    public void addSecondMenuEvents(Menu secondMenu) {
        secondMenu.setOnShowing(e -> secondMenu.setStyle("-fx-background-color: " + myResources.getString("menuColor")));
        secondMenu.setOnHidden(e -> secondMenu.setStyle("-fx-background-color: " + myResources.getString("menuHoverColor")));
        // create undo menu option
        MenuItem itemUndo = new MenuItem("Undo");
//        itemUndo.setOnAction(e -> undo());
        // create redo menu option
        MenuItem itemRedo = new MenuItem("Redo");
//        itemUndo.setOnAction(e -> redo());
        // create copy object menu option
        MenuItem itemCopy = new MenuItem("Copy");
//        itemUndo.setOnAction(e -> copy());
        // create paste object menu option
        MenuItem itemPaste = new MenuItem("Paste");
//        itemUndo.setOnAction(e -> redo());
        // create delete object menu option
        MenuItem itemDelete = new MenuItem("Delete");
//        itemUndo.setOnAction(e -> redo());
        secondMenu.getItems().addAll(itemUndo, itemRedo, itemCopy, itemPaste, itemDelete);
    }

    /**
     * Adds sub-items and their actions to the first menu of the
     * screen.
     * <p>
     * Note: Styles for the Menu are set here rather than in CSS because CSS
     * only supports the :hover selector when the mouse is directly hovering
     * over the object.
     *
     * @param firstMenu is the menu to which events are added
     */
    public void addFirstMenuEvents(Menu firstMenu) {
        firstMenu.setOnShowing(e -> firstMenu.setStyle("-fx-background-color: " + myResources.getString("menuColor")));
        firstMenu.setOnHidden(e -> firstMenu.setStyle("-fx-background-color: " + myResources.getString("menuHoverColor")));
        // create new editor menu option
        MenuItem itemNew = new MenuItem("New Project");
        itemNew.setOnAction(e -> exitPrompt(true));
        // create open editor file menu option
        MenuItem itemOpen = new MenuItem("Open Project");
        itemOpen.setOnAction(e -> myIO.openEditorFile());
        // create save editor file menu option
        MenuItem itemSave = new MenuItem("Save Project As...");
        itemSave.setOnAction(e -> myIO.saveEditorFile());
        // crate exit editor menu option
        MenuItem itemExit = new MenuItem("Exit GameEditor");
        itemExit.setOnAction(e -> exitPrompt(false));
        firstMenu.getItems().addAll(itemNew, itemOpen, itemSave, itemExit);
    }

}
