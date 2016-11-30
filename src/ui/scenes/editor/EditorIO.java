package ui.scenes.editor;

import editor.EditorController;
import javafx.stage.Stage;
import ui.FileBrowser;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class EditorIO {

    private Stage myStage;
    private EditorController myEditorController;
    private ResourceBundle myResources;

    EditorIO(Stage stage, EditorController editorController, ResourceBundle resources) {
        myStage = stage;
        myEditorController = editorController;
        myResources = resources;
    }

    /**
     * Gets the path to a file to save
     *
     * @return a File object containing the path
     */
    public boolean saveEditorFile() {
        File gameFile = new FileBrowser().saveEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEditor(gameFile.getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the path to a file to open
     *
     * @return a File object containing the path
     */
    public void openEditorFile() {
        File gameFile = new FileBrowser().openEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.loadEditor(gameFile.getAbsolutePath());
        }
    }

}
