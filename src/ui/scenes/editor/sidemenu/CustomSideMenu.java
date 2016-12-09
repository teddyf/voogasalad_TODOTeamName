package ui.scenes.editor.sidemenu;

import block.BlockType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import ui.builder.ComponentProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Created by Harshil Garg on 12/8/16.
 */
public class CustomSideMenu extends SideMenu {

    protected EditorControls myControls;

    public CustomSideMenu(Parent root, ResourceBundle resources, EditorControls controls) {
        super(root, resources);
        myControls = controls;
        init();
    }

    private ScrollPane addCustomItemScrollPane() {
        FlowPane pane = createFlowPane();

        // ComboBox to select the new item's BlockType
        ComboBox<BlockType> typeComboBox = new ComboBox<>();
        ObservableList<BlockType> options = FXCollections.observableArrayList(BlockType.values());
        typeComboBox.setItems(options);

        // ComboBox to inform the user
        TextField rowInput = (TextField) myBuilder.addNewTextField(pane, new ComponentProperties(10, 10).text("anal"));
        TextField columnInput = (TextField) myBuilder.addNewTextField(pane, new ComponentProperties(100, 100).text("anal2"));

        myBuilder.addComponent(pane, typeComboBox);

        Button fileBrowserButton = (Button) myBuilder.addNewButton(pane, new ComponentProperties(10, 10).text("hi"));

        fileBrowserButton.setOnMouseClicked(event -> {

            FileChooser browser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Portable Network Graphics (*.png)", "*.png");
            browser.getExtensionFilters().add(filter);

            File file = browser.showOpenDialog(myRoot.getScene().getWindow());

            if (customItemError(file, typeComboBox, rowInput, columnInput))
                return;

            Path source = Paths.get(file.getPath());

            Path destination = Paths.get("src/resources/images/tiles/" + typeComboBox.getValue().name().toLowerCase() +
                    "/" + source.getFileName().toString());

            if (new File(destination.toString()).exists()) {
                alert("You can't override previously added files!");
                return;
            }

            try {
                Files.copy(source, destination);
                int r = Integer.parseInt(rowInput.getText());
                int c = Integer.parseInt(columnInput.getText());
                if (r > 1 || c > 1) {
                    String fullPath = new File(destination.toString()).toURI().toString();
                    new ImageCropper(destination.toString(), r, c);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            ItemSideMenu ism = (ItemSideMenu) myControls.getMyItemMenu();
            ism.lol();

        });


        //myBuilder.addComponent(pane, fileBrowserButton);
        return new ScrollPane(pane);
    }

    private boolean customItemError(File file, ComboBox<BlockType> typeComboBox,
                                    TextField rowInput, TextField columnInput) {

        if (file == null) {
            alert("Please select a valid image file.");
            return true;
        }

        if (file.getName().replace(".png", "").contains(".") ||
                file.getName().replace(".png", "").contains("_")) {
            alert("File name cannot have any special characters.");
            return true;
        }

        if (typeComboBox.getValue() == null) {
            alert("Block type not specified.");
            return true;
        }

        int row = 0;
        int column = 0;
        try {
            row = Integer.parseInt(rowInput.getText());
            column = Integer.parseInt(columnInput.getText());
            if (row < 1 || column < 1) {
                alert("Invalid rows or columns given.");
                return true;
            }
        }

        catch (Exception e) {
            alert("Row and column number not specified.");
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
        Tab tab = createTab("Add Custom Items", addCustomItemScrollPane());
        myPanel.getTabs().add(tab);
    }

}
