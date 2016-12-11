package ui.scenes.editor;

import editor.EditorController;
import engine.EngineController;
import javafx.scene.Group;
import javafx.stage.Stage;
import ui.FileBrowser;
import ui.scenes.engine.EngineView;

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
class EditorIO {

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
    boolean saveEditorFile() {
        File gameFile = new FileBrowser().saveEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEditor(gameFile.getAbsolutePath());
            return true;
        }
        return false;
    }

    /**
     * Gets the myIconPath to a file to open
     *
     * @return true if opening successful, false otherwise
     */
    boolean openEditorFile() {
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
    boolean saveGameFile() {
        File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile != null) {
            myEditorController.saveEngine(gameFile.getAbsolutePath());
            return true;
        }
        return false;
    }


    /**
     * Runs an instance of the current game within the editor
     */
    void runGameInEditor() {
        Stage gameStage = new Stage();
        EngineView gameView = new EngineView(gameStage, new Group());
        EngineController engineController = myEditorController.runEngine();
        if (engineController != null) {
            gameView.setController(engineController);
            gameView.runInstance();
            gameStage.setScene(gameView);
        }
    }
}
