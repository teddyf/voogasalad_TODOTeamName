package editor;

import java.util.*;
import java.util.ResourceBundle;
import sun.security.tools.policytool.Resources;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import grid.*;
import ObjectMenuObjects.*;
import ui.builder.*;

public class SidePanel {
    private static final String SIDEPANEL_RESOURCES = "resources/sidepanel";
    private Group myRegion;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private SidePanelMenuObjects handler;

    public SidePanel (Group region) {
        myBuilder = new UIBuilder();
        handler = new SidePanelMenuObjects();
        myRegion = region;
        myResources = Resources.getBundle(SIDEPANEL_RESOURCES);
        initSidePanel();
    }

    private Tab createTab (String label, ScrollPane scrollPane) {
        Tab tab = new Tab();
        tab.setText(myResources.getString(label));
        tab.setContent(scrollPane);
        return tab;
    }
    
    private void populatePane(ScrollPane pane){
        List<GameObjects> list = handler.getDecorations();
        int buffer = 0;
        for(GameObjects obj: list){
            Node node = myBuilder.addCustomButton(pane, obj.getPath(), buffer, 0, 50);
            node.setOnMouseClicked(e->{handler.select(obj);});
            buffer += 50;
        }
    }

    private ScrollPane createScrollPane (Node content) {
        ScrollPane scrollPane = new ScrollPane(content);
        return scrollPane;
    }

    private void initSidePanel () {
        TabPane tp = new TabPane();
        ScrollPane decPane = createScrollPane(new Rectangle(20, 20, Color.ALICEBLUE));
        ScrollPane objPane = createScrollPane(new Rectangle(20, 20, Color.ANTIQUEWHITE));
        Tab decTab =
                createTab("decoration", decPane);
        populatePane(decPane);
        Tab objTab =
                createTab("obstacle", createScrollPane(new Rectangle(20, 20, Color.ANTIQUEWHITE)));
        populatePane(objPane);
        Tab switchTab = createTab("switch", createScrollPane(new Rectangle(20, 20, Color.AZURE)));
        Tab npcTab = createTab("NPC", createScrollPane(new Rectangle(20, 20, Color.BISQUE)));
        tp.getTabs().addAll(decTab, objTab, switchTab, npcTab);
        tp.setSide(Side.TOP);
        myRegion.getChildren().add(tp);
    }
    
}
