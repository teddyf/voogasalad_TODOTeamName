package ui.scenes.editor.sidemenu;

import block.BlockType;
import com.sun.javafx.scene.traversal.Direction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class adds functionality for the grid's control panel.
 */
public class GridSideMenu extends SideMenu {

    GridSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        init();
    }


    private ScrollPane resizePane() {
        FlowPane pane = new FlowPane();

        ComboBox<Direction> directionComboBox = new ComboBox<>();
        ObservableList<Direction> options = FXCollections.observableArrayList(Direction.values());
        directionComboBox.setItems(options);

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

