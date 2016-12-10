package ui.scenes.editor.sidemenu;

import block.BlockType;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

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


    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        List<Tab> tabs = new ArrayList<>();

        myPanel.getTabs().addAll(tabs);
    }


}

