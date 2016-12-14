package view.scenes.editor.sidemenu;

import model.block.blocktypes.BlockType;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import utilities.builder.ComponentProperties;
import view.scenes.editor.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Nisa, Pim, Harshil,
 *         <p>
 *         This class provides functionality for the item side menu, with which
 *         users can place items on the overworld.
 */
public class ItemSideMenu extends SideMenu {

    private ItemViewer myViewer;
    private final BlockType[] blockTypes = {BlockType.GROUND, BlockType.DECORATION,
            BlockType.OBSTACLE, BlockType.SWITCH_FLOOR, BlockType.GATE, BlockType.TELEPORT};

    ItemSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        myViewer = new ItemViewer();
        init();
    }

    /**
     * @return the currently selected object
     */
    public GameObject getSelected() {
        return myViewer.getSelected();
    }

    /**
     * Refreshes the item menu, presumably after a new custom item has been added
     */
    void refresh() {
        myViewer = new ItemViewer();
        myPanel.getTabs().clear();
        init();
    }

    /**
     * Adds an event handler to an object that both adds a CSS effect depending on
     * whether or not an object is currently selected and selects the object for
     * the back end
     *
     * @param object the object to which the event handler is added
     * @param item   the corresponding overworld item
     */
    private void addEventHandler(Node object, GameObject item) {
        object.setOnMouseClicked(e -> {
            if (myViewer.getSelected() != null) {
                // set current to unselected
                ImageView oldIcon = myViewer.getSelected().getIcon();
                resetHoverEffect(oldIcon);
            }
            if (myViewer.getSelected() == item) {
                // deselect object
                resetHoverEffect(item.getIcon());
                myViewer.select(null);
            } else {
                // set new selected to selected
                setChanged();
                notifyObservers(true);
                item.getIcon().setStyle(myResources.getString("selectedEffect"));
                item.getIcon().setOnMouseEntered(f -> item.getIcon().setStyle(myResources.getString("selectedEffect")));
                item.getIcon().setOnMouseExited(f -> item.getIcon().setStyle(myResources.getString("selectedEffect")));
                myViewer.select(item);
            }
        });
    }

    /**
     * Creates the scroll pane that holds the objects that can be added to the overworld
     *
     * @param type is the type of object being added
     * @return the ScrollPane populated with its objects
     */
    ScrollPane createScrollPane(BlockType type) {
        FlowPane itemPane = createFlowPane();
        List<GameObject> gameObjects = myViewer.getObjects(type);
        for (GameObject gameObject : gameObjects) {
            String iconPath = gameObject.getIconPath();
            // only display down-facing sprites
            if (type == BlockType.NPC && !iconPath.contains(myResources.getString("spriteDisplayDirection")))
                continue;
            ImageView icon = (ImageView) myBuilder.addNewImageView(itemPane, new ComponentProperties()
                    .path(iconPath)
                    .width(myUtil.getIntProperty("itemWidth"))
                    .preserveRatio(true)
                    .id(myResources.getString("gameObjectCSSid")));
            gameObject.setIcon(icon);
            addEventHandler(icon, gameObject);
        }
        return new ScrollPane(itemPane);
    }

    /**
     * Creates and then adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        for (BlockType type : blockTypes) {
            Tab tab = createTab(toTitleCase(type.name()), createScrollPane(type));
            tabs.add(tab);
        }
        myPanel.getTabs().addAll(tabs);
    }
}