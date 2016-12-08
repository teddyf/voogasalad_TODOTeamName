package ui.scenes.editor;

import block.BlockType;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import resources.properties.PropertiesUtilities;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import ui.scenes.editor.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nisa, Pim, Harshil
 *         <p>
 *         This class initializes tab-based UI used to choose ui.scenes.editor.objects to place
 *         on the overworld grid editor.
 */
public class ItemMenuUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private ItemViewer myViewer;
    private PropertiesUtilities myUtil;

    private DraggableTabPane itemPanel;

    private final BlockType [] blockTypes = {BlockType.GROUND, BlockType.DECORATION,
            BlockType.OBSTACLE, BlockType.SWITCH_FLOOR, BlockType.TELEPORT};

    public ItemMenuUI(Parent root, ResourceBundle resources) {
        myRoot = root;
        myResources = resources;
        myUtil = new PropertiesUtilities(myResources);
        myBuilder = new UIBuilder();
        myViewer = new ItemViewer();
    }

    private FlowPane getFlowPane() {
        FlowPane itemPane = new FlowPane();

        int padding = myUtil.getIntProperty("contentPadding");
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding, padding, padding, padding));

        return itemPane;
    }

    private ScrollPane createScrollPane(BlockType type) {
        FlowPane itemPane = getFlowPane();
        List<GameObject> list = myViewer.getObjects(type);
        System.out.println("b");
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
                    myViewer.getSelected().getIcon().setStyle("-fx-effect: none");
                }
                myViewer.select(object);
                object.getIcon().setStyle("-fx-effect: dropshadow(gaussian, black, 8, 0.0, 2, 0);");
            });
        }
        System.out.println("hi");
        return new ScrollPane(itemPane);
    }

    /**
     * Creates a Tab that can be added to a TabPane
     *
     * @param text       is the text to be displayed in the tag
     * @param scrollPane is the ScrollPane holding the content of the tab
     * @return the newly created tab
     */
    private Tab createTab(String text, ScrollPane scrollPane) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setContent(scrollPane);
        newTab.setClosable(false);
        return newTab;
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     *
     * @param itemPanel is the Item Menu to which the tabs are added
     */
    private void addTabs(DraggableTabPane itemPanel) {
        List<Tab> tabs = new ArrayList<>();
        for (BlockType type : blockTypes) {
            System.out.println(type);
            if (createScrollPane(type) == null)
                System.out.println("pen1");
            if (type.name() == null)
                System.out.println("pen2");
            Tab tab = createTab(type.name().toLowerCase(), createScrollPane(type));
            tabs.add(tab);
        }
        itemPanel.getTabs().addAll(tabs);
    }

    /**
     * Creates the item panel
     *
     * @return the item panel
     */
    private DraggableTabPane createItemPanel() {
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        DraggableTabPane itemPanel = new DraggableTabPane();
        itemPanel.setLayoutX(util.getIntProperty( "itemMenuX"));
        itemPanel.setLayoutY(util.getIntProperty( "itemMenuY"));
        itemPanel.setMinWidth(util.getIntProperty( "itemMenuWidth"));
        itemPanel.setMinHeight(util.getIntProperty( "itemMenuHeight"));
        return itemPanel;
    }

    public DraggableTabPane getItemPanel() {
        return itemPanel;
    }

    public void init() {
        itemPanel = createItemPanel();
        addTabs(itemPanel);
    }

    public GameObject getSelected() {
        return myViewer.getSelected();
    }
}