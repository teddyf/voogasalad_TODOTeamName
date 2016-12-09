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
 * @author Harshil Garg
 */
public abstract class SideMenu {

    protected Parent myRoot;
    protected ResourceBundle myResources;
    protected UIBuilder myBuilder;
    PropertiesUtilities myUtil;
    DraggableTabPane myPanel;

    SideMenu(Parent root, ResourceBundle resources) {
        myRoot = root;
        myResources = resources;
        myUtil = new PropertiesUtilities(myResources);
        myBuilder = new UIBuilder();
        myPanel = new DraggableTabPane();
    }

    public void init() {
        configureItemPanel();
        addTabs();
    }

    protected abstract void addTabs();

    FlowPane createFlowPane() {
        int padding = myUtil.getIntProperty("contentPadding");
        FlowPane itemPane = new FlowPane();
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding));
        return itemPane;
    }

    String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean firstLetter = true;
        for (char c : input.toCharArray()) {
            if (firstLetter) {
                c = Character.toTitleCase(c);
            } else if (c == '_') {
                c = ' ';
            } else {
                c = Character.toLowerCase(c);
            }
            firstLetter = false;
            titleCase.append(c);
        }
        return titleCase.toString();
    }

    Tab createTab(String text, ScrollPane scrollPane) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setContent(scrollPane);
        newTab.setClosable(false);
        return newTab;
    }

    private void configureItemPanel() {
        myPanel.setLayoutX(myUtil.getIntProperty("sidePanelX"));
        myPanel.setLayoutY(myUtil.getIntProperty("sidePanelY"));
        myPanel.setMinWidth(myUtil.getIntProperty("sidePanelWidth"));
        myPanel.setMinHeight(myUtil.getIntProperty("sidePanelHeight"));
    }

    DraggableTabPane getPanel() {
        return myPanel;
    }
}
