package view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Robert Steilberg
 */
public class FileBrowser {

    /**
     * Open a game file for playing
     *
     * @param currStage current JavaFX stage
     * @param path      path to the file to open
     * @return the chosen File
     */
    public File openGameFile(Stage currStage, String path) {
        FileChooser browser = setUpEngineFileChooser(path);
        File chosenXMLFile = browser.showOpenDialog(currStage);
        return chosenXMLFile;
    }

    /**
     * Save a game file
     *
     * @param currStage current JavaFX stage
     * @param path      path to the file to save
     * @return the chosen File
     */
    public File saveGameFile(Stage currStage, String path) {
        FileChooser browser = setUpEngineFileChooser(path);
        File chosenXMLFile = browser.showSaveDialog(currStage);
        // TODO: test that file is a valid gamefile here
        return chosenXMLFile;
    }

    /**
     * Open an editor file for editing
     *
     * @param currStage current JavaFX stage
     * @param path      path to the file to open
     * @return the chosen File
     */
    public File openEditorFile(Stage currStage, String path) {
        FileChooser browser = setUpEditorFileChooser(path);
        File chosenXMLFile = browser.showOpenDialog(currStage);
        // TODO: test that file is a valid gamefile here
        return chosenXMLFile;
    }

    /**
     * Save an editor file
     *
     * @param currStage current JavaFX stage
     * @param path      path to the file to save
     * @return the chosen File
     */
    public File saveEditorFile(Stage currStage, String path) {
        FileChooser browser = setUpEditorFileChooser(path);
        File chosenXMLFile = browser.showSaveDialog(currStage);
        // TODO: test that file is a valid gamefile here
        return chosenXMLFile;
    }

    /**
     * Configure the file chooser to save with the correct extensions
     *
     * @param path of the file
     * @return the FileChooser used for opening and saving editor files
     */
    private FileChooser setUpEditorFileChooser(String path) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "EDITOR files (*.editor)",
                "*.editor");
        browser.getExtensionFilters().add(filter);
        browser.setInitialDirectory(new File(path));
        return browser;
    }

    /**
     * Configure the file chooser to save with the correct extensions
     *
     * @param path of the file
     * @return the FileChooser used for opening and saving engine files
     */
    private FileChooser setUpEngineFileChooser(String path) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "ENGINE files (*.engine)",
                "*.engine");
        browser.getExtensionFilters().add(filter);
        browser.setInitialDirectory(new File(path));
        return browser;
    }
}
