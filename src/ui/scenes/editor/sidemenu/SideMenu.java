package ui.scenes.editor.sidemenu;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * Created by Harshil Garg on 12/8/16.
 */
public abstract class SideMenu {

    protected Parent myRoot;
    protected ResourceBundle myResources;
    protected UIBuilder myBuilder;
    protected PropertiesUtilities myUtil;
    protected DraggableTabPane myPanel;
    protected String myResourceName;

    public SideMenu(Parent root, ResourceBundle resources, String resourceName) {
        myRoot = root;
        myResources = resources;
        myResourceName = resourceName;
        myUtil = new PropertiesUtilities(resources);
        myPanel = new DraggableTabPane();
        myBuilder = new UIBuilder();
    }

    public void init() {
        configureItemPanel();
        addTabs();
    }

    protected abstract void addTabs();

    protected FlowPane getFlowPane() {
        int padding = myUtil.getIntProperty("contentPadding");

        FlowPane itemPane = new FlowPane();
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding));

        return itemPane;
    }

    protected Tab createTab(String text, ScrollPane scrollPane) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setContent(scrollPane);
        newTab.setClosable(false);
        return newTab;
    }

    private void configureItemPanel() {
        myPanel.setLayoutX(myUtil.getIntProperty(myResourceName + "MenuX"));
        myPanel.setLayoutY(myUtil.getIntProperty(myResourceName + "MenuY"));
        myPanel.setMinWidth(myUtil.getIntProperty(myResourceName + "MenuWidth"));
        myPanel.setMinHeight(myUtil.getIntProperty(myResourceName + "MenuHeight"));
    }

    public DraggableTabPane getItemPanel() {
        return myPanel;
    }


}
