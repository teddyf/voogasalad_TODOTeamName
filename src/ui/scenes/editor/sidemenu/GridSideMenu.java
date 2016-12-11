package ui.scenes.editor.sidemenu;

import editor.EditorController;
import grid.GridGrowthDirection;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

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
        myPanel.setMinHeight(400);
        myPanel.setMaxHeight(400);
    }

    private boolean invalidValue(GridGrowthDirection dir, String val) {
        try {
            Integer.parseInt(val);
            return dir == null;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    private ScrollPane createLinkPane() {
        Pane linkPanel = new Pane();
        Node button = myBuilder.addCustomButton(linkPanel,"LINK",20,20,100);
        button.setOnMouseClicked(e -> {

        });
        return new ScrollPane(linkPanel);
    }


    private ScrollPane createGridResizePane() {
        Pane resizePanel = new Pane();

        ToggleGroup radioGroup = new ToggleGroup();
        myBuilder.addCustomRadioButton(resizePanel, "Increase size", 20, 20, radioGroup, true, "grid-button");
        myBuilder.addCustomRadioButton(resizePanel, "Decrease size", 20, 60, radioGroup, false, "grid-button");

        myBuilder.addCustomLabel(resizePanel, "Grid side from which to\nadd or remove blocks", 20, 120, null, Color.WHITE, 15);
//        myBuilder.addCustomLabel(resizePanel, "add or remove blocks", 20, 135, null, Color.WHITE, 25);


        @SuppressWarnings("unchecked")
        ComboBox<GridGrowthDirection> directionComboBox = (ComboBox<GridGrowthDirection>) myBuilder.addNewComboBox(resizePanel,
                new ComponentProperties<GridGrowthDirection>(190, 125)
                        .options(FXCollections.observableArrayList(GridGrowthDirection.values())));

        myBuilder.addCustomLabel(resizePanel, "Number of rows or columns to add or remove:", 20, 200, null, Color.WHITE, 15);
        TextField sizeInput = (TextField) myBuilder.addNewTextField(resizePanel, new ComponentProperties(20, 230).text("block size"));

        Button button = (Button) myBuilder.addNewButton(resizePanel, new ComponentProperties(20, 300).text("Resize"));

        directionComboBox.valueProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        button.setDisable(true);

        button.setOnMouseClicked(e -> {
            try {
                myEditorController.changeGridSize(directionComboBox.getValue(), Integer.parseInt(sizeInput.getText()));
            } catch (ArrayIndexOutOfBoundsException exc) {
                myBuilder.addNewAlert("Error","Error");
            }
        });

        return new ScrollPane(resizePanel);
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        Tab resizeTab = createTab("Resize", createGridResizePane());
        Tab linkTab = createTab("Link", createLinkPane());
        myPanel.getTabs().addAll(resizeTab,linkTab);
    }

}

