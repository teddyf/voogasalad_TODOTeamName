package ui.scenes.editor;

import com.sun.xml.internal.ws.api.pipe.Engine;
import editor.EditorController;
import engine.EngineController;
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
    private GridUI myGrid;
    private EngineController myEngineController;
    private ResourceBundle myResources;

    EditorIO(Stage stage, EditorController editorController, EngineController engineController, ResourceBundle resources, GridUI grid) {
        myStage = stage;
        myEditorController = editorController;
        myEngineController = engineController;
        myResources = resources;
        myGrid = grid;
    }

    public boolean saveGameFile() {
        File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEngine(gameFile.getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public void openGameFile() {
        File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEngineController.loadEngine(gameFile.getAbsolutePath());
            myGrid.loadGrid();
        }
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
            myGrid.loadGrid();
        }
    }

}
