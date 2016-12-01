package ui.scenes.editor;

import editor.EditorController;
import engine.EngineController;
import javafx.stage.Stage;
import ui.FileBrowser;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles IO for the game authoring engine. Functionality includes saving an
 *         editor state to re-open and continue building a game later, opening an editor state,
 *         exporting an editor state to a game file to be played, and opening a game file to play
 *         in the engine.
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

    /**
     * Saves an editor file that can be re-opened later for editing
     *
     * @return true is saving successful, false otherwise
     */
    public boolean saveEditorFile() {
        File gameFile = new FileBrowser().saveEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEditor(gameFile.getAbsolutePath());
            return true;
        }
        return false;
    }

    /**
     * Gets the path to a file to open
     *
     * @return true if opening successful, false otherwise
     */
    public boolean openEditorFile() {
        File gameFile = new FileBrowser().openEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.loadEditor(gameFile.getAbsolutePath());
            myGrid.loadGrid(); // load into grid user interface
            return true;
        }
        return false;
    }

    /**
     * Exports a game file that can be played with the engine
     *
     * @return true if export successful, false otherwise
     */
    public boolean saveGameFile() {
        File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEngine(gameFile.getAbsolutePath());
            return true;
        }
        return false;
    }

    /**
     * Opens a game file to play in the engine
     *
     * @return true if game file successfully opened, false otherwise
     */
    public boolean openGameFile() {
        File gameFile = new FileBrowser().openEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEngineController.loadEngine(gameFile.getAbsolutePath());
            return true;
        }
        return false;
    }

    public void runGameInEditor() {
        EngineController loadedEngine = myEditorController.runEngine();
        // TODO continue running game
    }
}
