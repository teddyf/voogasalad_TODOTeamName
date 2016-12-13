package ui.scenes.editor.sidemenu;

import block.blocktypes.BlockType;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ui.builder.ComponentProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * @author Harshil Garg
 */
public class CustomSideMenu extends SideMenu {

    private EditorControls myControls;

    CustomSideMenu(Parent root, ResourceBundle resources, EditorControls controls) {
        super(root, resources);
        myControls = controls;
        init();
        myPanel.setMinHeight(400);
        myPanel.setMaxHeight(400);
    }

    /**
     * Tests the inputs to make sure they are correctly formatted
     *
     * @param blockType the BlockType given by the ComboBox
     * @param row       the row value of the TextField
     * @param col       the col value of the TextField
     * @return true if the value is invalid, false otherwise
     */
    private boolean invalidValue(BlockType blockType, String row, String col) {
        try {
            Integer.parseInt(row);
            Integer.parseInt(col);
            return blockType == null;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    private ScrollPane addCustomSoundScrollPane() {
        Pane addSoundPanel = new Pane();
        myBuilder.addCustomLabel(addSoundPanel, "Import a new sound into the editor by\nimporting an MP3 file.", 20, 20, null, Color.WHITE, 20);
        Button addButton = (Button) myBuilder.addNewButton(addSoundPanel, new ComponentProperties(20, 100).text("Add New Sound"));


        addButton.setOnMouseClicked(event -> {

            FileChooser browser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MP3, M4A, AIFF, AVI, MPG, MP2, WAV", "*.mp3", "*.m4a", "*.aiff", "*.avi", "*.mpg", "*.mp2", "*.wav");
            browser.getExtensionFilters().add(filter);

            File file = browser.showOpenDialog(myRoot.getScene().getWindow());

            Path source = Paths.get(file.getPath());

            Path destination = Paths.get("src/resources/sounds/" + source.getFileName().toString());

            if (new File(destination.toString()).exists()) {
                alert("You can't overwrite previously added files.");
                return;
            }

            try {
                Files.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }

            GameSideMenu gsm = myControls.getGridSideMenu();
            gsm.refresh();

        });
        return new ScrollPane(addSoundPanel);
    }

    private ScrollPane addCustomItemScrollPane() {
        Pane addItemPanel = new Pane();

        // ComboBox to select the new item's BlockType
        myBuilder.addCustomLabel(addItemPanel, "Import a new item into the editor by\nchoosing an image and specifying its\nheight and width in blocks.", 20, 20, null, Color.WHITE, 20);

        myBuilder.addCustomLabel(addItemPanel, "Choose the appropriate\nblock type for the new object", 20, 130, null, Color.WHITE, 15);


        @SuppressWarnings("unchecked")
        ComboBox<BlockType> blockTypeComboBox = (ComboBox<BlockType>) myBuilder.addNewComboBox(addItemPanel,
                new ComponentProperties<BlockType>(230, 135)
                        .options(FXCollections.observableArrayList(BlockType.values())));

        myBuilder.addCustomLabel(addItemPanel, "Number of rows", 25, 205, null, Color.WHITE, 15);
        myBuilder.addCustomLabel(addItemPanel, "Number of columns", 25, 255, null, Color.WHITE, 15);


        TextField rowInput = (TextField) myBuilder.addNewTextField(addItemPanel, new ComponentProperties(200, 200).text("row").width(50));
        TextField colInput = (TextField) myBuilder.addNewTextField(addItemPanel, new ComponentProperties(200, 250).text("col").width(50));

        Button addButton = (Button) myBuilder.addNewButton(addItemPanel, new ComponentProperties(20, 300).text("Add New Object"));

        blockTypeComboBox.valueProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));

        rowInput.textProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));
        colInput.textProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));
        addButton.setDisable(true);

        addButton.setOnMouseClicked(event -> {

            FileChooser browser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Portable Network Graphics (*.png)", "*.png");
            browser.getExtensionFilters().add(filter);

            File file = browser.showOpenDialog(myRoot.getScene().getWindow());

            if (customItemError(file))
                return;

            Path source = Paths.get(file.getPath());

            Path destination = Paths.get("src/resources/images/tiles/" + blockTypeComboBox.getValue().name().toLowerCase() +
                    "/" + source.getFileName().toString());

            if (new File(destination.toString()).exists()) {
                alert("You can't overwrite previously added files.");
                return;
            }

            try {
                Files.copy(source, destination);
                int r = Integer.parseInt(rowInput.getText());
                int c = Integer.parseInt(colInput.getText());
                if (r > 1 || c > 1) {
                    String fullPath = new File(destination.toString()).toURI().toString();
                    new ImageCropper(destination.toString(), r, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ItemSideMenu ism = myControls.getMyItemMenu();
            ism.refresh();

        });

        return new ScrollPane(addItemPanel);
    }

    private boolean customItemError(File file) {
        if (file == null) {
            return true;
        }
        if (file.getName().replace(".png", "").contains(".") ||
                file.getName().replace(".png", "").contains("_")) {
            alert("File name cannot have any special characters.");
            return true;
        }
        return false;
    }

    private void alert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(false);
        alert.setTitle("");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void addTabs() {
        Tab newImageTab = createTab("Add New Items", addCustomItemScrollPane());
        Tab newSoundTab = createTab("Add New Sounds", addCustomSoundScrollPane());
        myPanel.getTabs().addAll(newImageTab, newSoundTab);
    }

}
