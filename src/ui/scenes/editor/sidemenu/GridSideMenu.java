package ui.scenes.editor.sidemenu;

import block.BlockType;
import com.sun.javafx.scene.traversal.Direction;
import editor.EditorController;
import grid.GridGrowthDirection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import ui.builder.ComponentProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class adds functionality for the grid's control panel.
 */
public class GridSideMenu extends SideMenu {

    private EditorController myEditorController;

    GridSideMenu(Parent root, ResourceBundle resources, EditorController editorController) {
        super(root, resources);
        myEditorController = editorController;
        init();
    }

    private boolean invalidValue(GridGrowthDirection dir, String val) {
        try {
            Integer.parseInt(val);
            return dir == null;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    private ScrollPane resizePane() {
        FlowPane pane = new FlowPane();

        ComboBox<GridGrowthDirection> directionComboBox = new ComboBox<>();
        ObservableList<GridGrowthDirection> options = FXCollections.observableArrayList(GridGrowthDirection.values());
        directionComboBox.setItems(options);

        TextField rowInput = (TextField) myBuilder.addNewTextField(pane, new ComponentProperties(10, 10).text("addition"));

        Button button = (Button) myBuilder.addNewButton(pane, new ComponentProperties(10, 10).text("submit"));

        directionComboBox.valueProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(),rowInput.getText())));
        rowInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(),rowInput.getText())));
        rowInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(),rowInput.getText())));
        button.setDisable(true);

        button.setOnMouseClicked(e -> {
            myEditorController.changeGridSize(directionComboBox.getValue(), Integer.parseInt(rowInput.getText()));
        });

        myBuilder.addComponent(pane, directionComboBox);

        return new ScrollPane(pane);
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        Tab tab = createTab("Resize", resizePane());
        myPanel.getTabs().add(tab);
    }


}

