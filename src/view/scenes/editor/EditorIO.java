package view.scenes.editor;

import controller.editor.EditorController;
import controller.engine.EngineController;
import javafx.scene.Group;
import javafx.stage.Stage;
import view.FileBrowser;
import view.scenes.engine.EngineView;

import java.io.File;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Aninda Manocha
 *         <p>
 *         This class handles IO for the game authoring controller.engine. Functionality includes saving an
 *         controller.editor state to re-open and continue building a game later, opening an controller.editor state,
 *         exporting an controller.editor state to a game file to be played, and opening a game file to play
 *         in the controller.engine.
 */
class EditorIO extends Observable {

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
     * Saves an controller.editor file that can be re-opened later for editing
     *
     * @return true is saving successful, false otherwise
     */
    boolean saveEditorFile() {
        File gameFile = new FileBrowser().saveEditorFile(myStage, myResources.getString("editorFilePath"));
        if (gameFile != null) {
            myEditorController.saveEditor(gameFile.getPath());
            setChanged();
            notifyObservers();
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
        File gameFile = new FileBrowser().openEditorFile(myStage, myResources.getString("editorFilePath"));
        if (gameFile != null) {
            myEditorController.loadEditor(gameFile.getPath());
            setChanged();
            notifyObservers(myEditorController);
            myGrid.loadGrid(); // load into model.grid user interface
            return true;
        }
        return false;
    }

    /**
     * Exports a game file that can be played with the controller.engine
     *
     * @return true if export successful, false otherwise
     */
    boolean saveGameFile() {
        File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("engineFilePath"));
        if (gameFile != null) {
            myEditorController.saveEngine(gameFile.getPath());
            return true;
        }
        return false;
    }

    /**
     * Runs an instance of the current game within the controller.editor
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
