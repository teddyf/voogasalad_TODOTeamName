package ui.scenes.editor.sidemenu;

import block.BlockType;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import ui.builder.ComponentProperties;
import ui.scenes.editor.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nisa, Pim, Harshil, Robert Steilberg
 *         <p>
 *         This class initializes tab-based UI used to choose ui.scenes.editor.objects to place
 *         on the overworld grid editor.
 */
public class ItemSideMenu extends SideMenu {

    private ItemViewer myViewer;
    private final BlockType[] blockTypes = {BlockType.GROUND, BlockType.DECORATION,
            BlockType.OBSTACLE, BlockType.SWITCH_FLOOR, BlockType.TELEPORT, BlockType.ENEMY};

    ItemSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        myViewer = new ItemViewer();
        init();
    }

    /**
     * Refreshes the item menu, presumably after a new item has been added
     */
    void refresh() {
        myViewer = new ItemViewer();
        myPanel.getTabs().clear();
        init();
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        for (BlockType type : blockTypes) {
            Tab tab = createTab(toTitleCase(type.name()), createScrollPane(type));
            tabs.add(tab);
        }
        myPanel.getTabs().addAll(tabs);
    }

    /**
     * Creates the scroll pane that holds the objects that can be added to the overworld
     *
     * @param type is the type of object being added
     * @return the ScrollPane populated with its objects
     */
    private ScrollPane createScrollPane(BlockType type) {
        FlowPane itemPane = createFlowPane();
        List<GameObject> list = myViewer.getObjects(type);
        for (GameObject object : list) {
            String path = object.getIconPath();
            ImageView icon = (ImageView) myBuilder.addNewImageView(itemPane, new ComponentProperties()
                    .path(path)
                    .width(myUtil.getIntProperty("itemWidth"))
                    .preserveRatio(true)
                    .id("game-object"));
            object.setIcon(icon);
            icon.setOnMouseClicked(e -> {
                if (myViewer.getSelected() != null) {
                    myViewer.getSelected().getIcon().setStyle(myResources.getString("deselectedEffect"));
                }
                myViewer.select(object);
                object.getIcon().setStyle(myResources.getString("selectedEffect"));
            });
        }
        return new ScrollPane(itemPane);
    }

    /**
     * @return the currently selected object
     */
    public GameObject getSelected() {
        return myViewer.getSelected();
    }
}