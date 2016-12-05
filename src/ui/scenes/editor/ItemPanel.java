package ui.scenes.editor;

import java.util.*;

import javafx.scene.layout.Pane;
import sun.security.tools.policytool.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.FlowPane;
import ui.scenes.editor.objects.*;
import ui.builder.*;

/**
 * @author Nisa, Pim, Aninda Manocha
 */

public class ItemPanel {

    private static final String ITEM_PANEL_RESOURCES_PATH = "resources/properties/item-panel";
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private Pane myRegion;
    private ItemPanelObjects myItemPanelObjects;

    ItemPanel(Pane region) {
        myResources = Resources.getBundle(ITEM_PANEL_RESOURCES_PATH);
        myBuilder = new UIBuilder();
        myRegion = region;
        myItemPanelObjects = new ItemPanelObjects();
        initSidePanel();
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

    ItemPanelObjects getMyItemPanelObjects() {
        return myItemPanelObjects;
    }

    private void initSidePanel() {
        TabPane itemPanel = new TabPane();
        ScrollPane groundPane = createScrollPane("Ground");
        Tab groundTab = createTab("Ground", groundPane);
        ScrollPane decorPane = createScrollPane("Decor");
        Tab decorTab = createTab("Decor", decorPane);
        ScrollPane obstaclePane = createScrollPane("Obstacles");
        Tab obstacleTab = createTab("Obstacles", obstaclePane);

        itemPanel.getTabs().addAll(groundTab, decorTab, obstacleTab);
        myRegion.getChildren().add(itemPanel);
    }



}