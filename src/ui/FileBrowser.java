package ui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Robert Steilberg
 */
public class FileBrowser {

    public File openGameFile(Stage currStage, String path) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        browser.getExtensionFilters().add(filter);
        browser.setInitialDirectory(new File(path));
        File chosenXMLFile = browser.showOpenDialog(currStage);
        // TODO: test that file is a valid gamefile here
        return chosenXMLFile;
    }

    public File saveGameFile(Stage currStage, String path) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        browser.getExtensionFilters().add(filter);
        browser.setInitialDirectory(new File(path));
        File chosenXMLFile = browser.showSaveDialog(currStage);
        // TODO: test that file is a valid gamefile here
        return chosenXMLFile;
    }

}
