package ui.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import resources.properties.PropertiesUtilities;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import ui.scenes.editor.objects.GameObject;
import ui.scenes.editor.objects.ItemPanelObjects;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nisa, Pim
 *         <p>
 *         This class initializes tab-based UI used to choose ui.scenes.editor.objects to place
 *         on the overworld grid editor.
 */
public class ItemMenuUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private ItemPanelObjects myItemPanelObjects;

    ItemMenuUI(Parent root, UIBuilder builder, ResourceBundle resources) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        myItemPanelObjects = new ItemPanelObjects();
    }

    /**
     * Adds a border to the grid based on the grid's position and size
     * by creating a Rectangle behind it
     *
     * @return the Rectangle representing the border
     */
    private void addBorder() {
        PropertiesUtilities util = new PropertiesUtilities();
        Rectangle border = new Rectangle();
        border.setLayoutX(util.getIntProperty(myResources, "itemMenuX") - util.getIntProperty(myResources, "borderSize"));
        border.setLayoutY(util.getIntProperty(myResources, "itemMenuY") - util.getIntProperty(myResources, "borderSize"));
        border.setWidth(util.getIntProperty(myResources, "itemMenuWidth") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setHeight(util.getIntProperty(myResources, "itemMenuHeight") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setId("grid-border");
        myBuilder.addComponent(myRoot, border);
    }

    /**
     * Gets the content for each scrollpane under each tab
     *
     * @param label is the label used to determine which content to get
     * @return the ScrollPane, populated with its content
     */
    private ScrollPane createScrollPane(String label) {
        PropertiesUtilities util = new PropertiesUtilities();
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        FlowPane itemPane = new FlowPane();
        int padding = util.getIntProperty(myResources, "contentPadding");
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding, padding, padding, padding));
        List<GameObject> list;
        switch (label) {
            case "Ground":
                list = myItemPanelObjects.getGroundObjs();
                break;
            case "Decor":
                list = myItemPanelObjects.getDecorObjs();
                break;
            case "Obstacles":
                list = myItemPanelObjects.getObstacleObjs();
                break;
            case "Switches":
                list = myItemPanelObjects.getSwitchObjs();
                break;
            case "Teleporter":
                list = myItemPanelObjects.getTeleObjs();
                break;
            default:
                throw new NullPointerException();
        }
        for (GameObject gameObj : list) {
            String imgPath = gameObj.getIconPath();
            ImageView objectIcon = (ImageView) myBuilder.addNewImageView(itemPane, new ComponentProperties()
                    .path(imgPath)
                    .width(util.getIntProperty(myResources, "itemWidth"))
                    .preserveRatio(true)
                    .id("game-object"));
            gameObj.setIcon(objectIcon);
            objectIcon.setOnMouseClicked(e -> {
                if (myItemPanelObjects.getSelected() != null) {
                    myItemPanelObjects.getSelected().getIcon().setStyle("-fx-effect: none");
                }
                myItemPanelObjects.select(gameObj);
                gameObj.getIcon().setStyle("-fx-effect: dropshadow(gaussian, black, 8, 0.0, 2, 0);");
            });
        }
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
    private void addTabs(TabPane itemPanel) {
        ScrollPane groundPane = createScrollPane("Ground");
        Tab groundTab = createTab("Ground", groundPane);
        ScrollPane decorPane = createScrollPane("Decor");
        Tab decorTab = createTab("Decor", decorPane);
        ScrollPane obstaclePane = createScrollPane("Obstacles");
        Tab obstacleTab = createTab("Obstacles", obstaclePane);
        ScrollPane switchPane = createScrollPane("Switches");
        Tab switchTab = createTab("Switches", switchPane);
        ScrollPane telePane = createScrollPane("Teleporter");
        Tab teleTab = createTab("Teleporter", telePane);
        itemPanel.getTabs().addAll(groundTab, decorTab, obstacleTab, switchTab, teleTab);
    }

    /**
     * Creates the item panel
     *
     * @return the item panel
     */
    private TabPane createItemPanel() {
        PropertiesUtilities util = new PropertiesUtilities();
        TabPane itemPanel = new TabPane();
        itemPanel.setLayoutX(util.getIntProperty(myResources, "itemMenuX"));
        itemPanel.setLayoutY(util.getIntProperty(myResources, "itemMenuY"));
        itemPanel.setMinWidth(util.getIntProperty(myResources, "itemMenuWidth"));
        itemPanel.setMinHeight(util.getIntProperty(myResources, "itemMenuHeight"));
        return itemPanel;
    }

    /**
     * Creates the tab-based menu that will hold the ui.scenes.editor.objects to be added to the
     * overworld grid.
     *
     * @return the item menu, properly placed on the grid
     */
    ItemPanelObjects getItemPanelObjects() {
        TabPane itemPanel = createItemPanel();
        addTabs(itemPanel);
        addBorder();
        myBuilder.addComponent(myRoot, itemPanel);
        return myItemPanelObjects;
    }
}