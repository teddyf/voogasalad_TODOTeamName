package ui.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.scenes.editor.objects.GameObjects;
import ui.scenes.editor.objects.ItemPanelObjects;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nisa, Pim, Robert Steilberg
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

    private Tab createTab(String key, ScrollPane scrollPane) {
        Tab newTab = new Tab();
        newTab.setText(key);
        newTab.setContent(scrollPane);
        newTab.setClosable(false);
        return newTab;
    }

    private ScrollPane createScrollPane(String label) {
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));

        FlowPane content = new FlowPane();
        content.setPrefWrapLength(300);
        content.setHgap(20);
        content.setVgap(20);
        content.setPadding(new Insets(20, 20, 20, 20));

        List<GameObjects> list;
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
            default:
                list = null;
                break;
        }

        for (GameObjects obj : list) {
            Node node = myBuilder.addCustomButton(content, obj.getPath(), 20, 0, 50);
            node.setOnMouseEntered(e -> node.setEffect(hoverOpacity));
            node.setOnMouseExited(e -> node.setEffect(null));
            node.setOnMouseClicked(e -> {
                myItemPanelObjects.select(obj);
                obj.getImage().setEffect(hoverOpacity);
            });
        }
        return new ScrollPane(content);
    }

    /**
     * Creates the tab-based menu that will hold the ui.scenes.editor.objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    ItemPanelObjects getItemPanelObjects() {
        TabPane itemPanel = new TabPane();
        itemPanel.setLayoutX(650);
        itemPanel.setLayoutY(45);
        itemPanel.setMinWidth(500);
        ScrollPane groundPane = createScrollPane("Ground");
        Tab groundTab = createTab("Ground", groundPane);
        ScrollPane decorPane = createScrollPane("Decor");
        Tab decorTab = createTab("Decor", decorPane);
        ScrollPane obstaclePane = createScrollPane("Obstacles");
        Tab obstacleTab = createTab("Obstacles", obstaclePane);
        ScrollPane switchPane = createScrollPane("Switches");
        Tab switchTab = createTab("Switches", switchPane);
        itemPanel.getTabs().addAll(groundTab, decorTab, obstacleTab, switchTab);
        myBuilder.addComponent(myRoot,itemPanel);
        return myItemPanelObjects;
    }
}