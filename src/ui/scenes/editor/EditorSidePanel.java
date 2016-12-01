package ui.scenes.editor;

import java.util.*;

import sun.security.tools.policytool.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.FlowPane;
import ObjectMenuObjects.*;
import ui.builder.*;

/**
 * @author Nisa, Pim
 */
public class EditorSidePanel {
    private static final String ITEMPANEL_RESOURCES_PATH = "resources/properties/item-panel";
    private Group myRegion;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private SidePanelMenuObjects handler;

    public EditorSidePanel (Group region) {
        myBuilder = new UIBuilder();
        handler = new SidePanelMenuObjects();
        myRegion = region;
        myResources = Resources.getBundle(ITEMPANEL_RESOURCES_PATH);
        initSidePanel();
    }

    public Tab createTab (String label, ScrollPane scrollPane) {
        Tab tab = new Tab();
        tab.setText(myResources.getString(label));
        tab.setContent(scrollPane);
        tab.setClosable(false);
        return tab;
    }

    private ScrollPane createScrollPane (String label) {
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        
        FlowPane content = new FlowPane();
        content.setPrefWrapLength(300);
        content.setHgap(20);
        content.setVgap(20);
        content.setPadding(new Insets(20, 20, 20, 20));
        
        List<GameObjects> list;
        if (label.equals("decoration")) {
            list = handler.getDecorations();
        } else if (label.equals("npc")) {
        	list = handler.getPlayers();
        }
        else {
            list = handler.getObstacles();
        }
        int buffer = 0;
        for (GameObjects obj : list) {
            Node node = myBuilder.addCustomButton(content, obj.getPath(), 20, 0, 50);
            node.setOnMouseEntered(e -> node.setEffect(hoverOpacity));
            node.setOnMouseExited(e -> node.setEffect(null));
            node.setOnMouseClicked(e -> {
                handler.select(obj); obj.getImage().setEffect(hoverOpacity);});
            buffer += 50;
        }
        ScrollPane scrollPane = new ScrollPane(content);
        return scrollPane;
    }

    private void initSidePanel () {
        TabPane tp = new TabPane();
        ScrollPane decPane = createScrollPane("decoration");
        ScrollPane objPane = createScrollPane("obstacles");
        ScrollPane switchPane = createScrollPane("switch");
        ScrollPane npcPane = createScrollPane("npc");
        Tab decTab =
                createTab("decoration", decPane);
        Tab objTab =
                createTab("obstacle", objPane);
        Tab switchTab = createTab("switch", switchPane);
        Tab npcTab = createTab("NPC", npcPane);
        tp.getTabs().addAll(decTab, objTab, switchTab, npcTab);
        tp.setSide(Side.TOP);
        myRegion.setStyle("-fx-effect: dropshadow(gaussian, black, 8, 0.0, 2, 0);");
        myRegion.getChildren().add(tp);
    }
    
    SidePanelMenuObjects getHandler(){
        return handler;
    }

}