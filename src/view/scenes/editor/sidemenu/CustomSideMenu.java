package view.scenes.editor.sidemenu;

import model.block.blocktypes.BlockType;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import utilities.builder.ComponentProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class provides functionality for importing custom items to the controller.editor for
 *         use in the overworld.
 */
public class CustomSideMenu extends SideMenu {

    private EditorControls myControls;

    CustomSideMenu(Parent root, ResourceBundle resources, EditorControls controls) {
        super(root, resources);
        myControls = controls;
        init();
        setSidePanelHeight(400);
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

    /**
     * Adds the event handler that triggers a file browser used to import a custom sound
     *
     * @param node the button to which the event handler is added
     */
    private void importSoundEventHandler(Node node) {
        node.setOnMouseClicked(event -> {

            FileChooser browser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser
                    .ExtensionFilter("MP3, M4A, AIFF, AVI, MPG, MP2, WAV",
                    "*.mp3", "*.m4a", "*.aiff", "*.avi", "*.mpg", "*.mp2", "*.wav");
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
            myControls.getGridSideMenu().refresh();
        });
    }

    /**
     * Creates the control panel used for importing new sounds to the controller.editor
     *
     * @return the Scrollpane holding the sound control panel
     */
    private ScrollPane customSoundPane() {
        Pane importSoundPanel = new Pane();
        myBuilder.addNewLabel(importSoundPanel, new ComponentProperties(20, 20)
                .text(myResources.getString("newSoundInstructions"))
                .color(Color.WHITE)
                .size(20));
        Node addButton = myBuilder.addNewButton(importSoundPanel, new ComponentProperties(20, 100)
                .text(myResources.getString("confirmButton")));
        importSoundEventHandler(addButton);
        return new ScrollPane(importSoundPanel);
    }

    /**
     * Adds the event handler that triggers a file browser used to import a custom object
     *
     * @param node the button to which the event handler is added
     */
    private void importObjectEventHandler(Node node, ComboBox<BlockType> blockTypeComboBox, TextField rowInput, TextField colInput) {
        node.setOnMouseClicked(event -> {
            FileChooser browser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Portable Network Graphics (*.png)", "*.png");
            browser.getExtensionFilters().add(filter);
            File file = browser.showOpenDialog(myRoot.getScene().getWindow());
            if (customItemError(file)) return;
            Path source = Paths.get(file.getPath());
            Path destination = Paths.get("src/resources/images/tiles/" + blockTypeComboBox.getValue().name().toLowerCase() +
                    "/" + source.getFileName().toString());
            if (new File(destination.toString()).exists()) {
                alert("You can't overwrite previously added files.");
                return;
            }
            try {
                int rows = Integer.parseInt(rowInput.getText());
                int columns = Integer.parseInt(colInput.getText());
                Files.copy(source, destination);
                if (rows > 1 || columns > 1) {
                    new ImageCropper(destination.toString(), rows, columns);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            myControls.getMyItemMenu().refresh();
        });
    }

    /**
     * Creates the control panel used for importing new game objects to the controller.editor
     *
     * @return the Scrollpane holding the control panel
     */
    private ScrollPane customItemPane() {
        Pane importItemPanel = new Pane();
        myBuilder.addNewLabel(importItemPanel, new ComponentProperties(20, 20)
                .text(myResources.getString("newItemInstructions1"))
                .color(Color.WHITE)
                .size(20));
        myBuilder.addNewLabel(importItemPanel, new ComponentProperties(20, 130)
                .text(myResources.getString("newItemInstructions2"))
                .color(Color.WHITE)
                .size(15));

        @SuppressWarnings("unchecked")
        ComboBox<BlockType> blockTypeComboBox = (ComboBox<BlockType>) myBuilder.addNewComboBox(importItemPanel,
                new ComponentProperties<BlockType>(230, 135)
                        .options(FXCollections.observableArrayList(BlockType.values())));

        myBuilder.addNewLabel(importItemPanel, new ComponentProperties(25, 205)
                .text(myResources.getString("numRowPrompt"))
                .color(Color.WHITE)
                .size(15));

        myBuilder.addNewLabel(importItemPanel, new ComponentProperties(25, 255)
                .text(myResources.getString("numColumnPrompt"))
                .color(Color.WHITE)
                .size(15));

        TextField rowInput = (TextField) myBuilder.addNewTextField(importItemPanel, new ComponentProperties(200, 200)
                .text("row").width(50));
        TextField colInput = (TextField) myBuilder.addNewTextField(importItemPanel, new ComponentProperties(200, 250)
                .text("col").width(50));

        Button addButton = (Button) myBuilder.addNewButton(importItemPanel, new ComponentProperties(20, 300)
                .text(myResources.getString("confirmButton")));

        blockTypeComboBox.valueProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));
        rowInput.textProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));
        colInput.textProperty().addListener(e -> addButton.setDisable(invalidValue(blockTypeComboBox.getValue(), rowInput.getText(), colInput.getText())));
        addButton.setDisable(true);
        importObjectEventHandler(addButton, blockTypeComboBox, rowInput, colInput);
        return new ScrollPane(importItemPanel);
    }

    /**
     * Tests a chosen file to ensure that it is valid
     *
     * @param file the file to test
     * @return true if the file is invalid, false otherwise
     */
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

    /**
     * Create a custom alert when an error is encountered
     *
     * @param content the error message displayed in the alert
     */
    private void alert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(false);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Creates and then adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        Tab newImageTab = createTab(myResources.getString("newItemTab"), customItemPane());
        Tab newSoundTab = createTab(myResources.getString("newSoundTab"), customSoundPane());
        myPanel.getTabs().addAll(newImageTab, newSoundTab);
    }

}
