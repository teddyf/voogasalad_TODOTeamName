package ui.scenes.editor.sidemenu;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class defines the basic functionality for a SideMenu in the Editor.
 */
public abstract class SideMenu extends Observable{

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
        myPanel.setId(myUtil.getStringProperty("panelTabsCSSid"));
    }

    public void init() {
        configureSidePanel();
        addTabs();
    }

    protected abstract void addTabs();

    FlowPane createFlowPane() {
        int padding = myUtil.getIntProperty("contentPadding");
        FlowPane itemPane = new FlowPane();
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding));
        itemPane.setPrefWrapLength(myUtil.getIntProperty("sidePanelWidth") - 60);
        return itemPane;
    }

    void resetHoverEffect(Node obj) {
        obj.setStyle(myResources.getString("deselectedEffect"));
        obj.setOnMouseEntered(f -> obj.setStyle(myResources.getString("selectedEffect")));
        obj.setOnMouseExited(f -> obj.setStyle(myResources.getString("deselectedEffect")));
    }

    /**
     * Converts a String to title case
     *
     * @param input the String to convert
     * @return the String in title case
     */
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

    /**
     * Creates a new tab
     *
     * @param text    the title text for the tab
     * @param content the tab's content
     * @return the tab with its content
     */
    Tab createTab(String text, Parent content) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setContent(content);
        newTab.setClosable(false);
        return newTab;
    }

    /**
     * Basic configuration for side menus
     */
    private void configureSidePanel() {
        myPanel.setLayoutX(myUtil.getIntProperty("sidePanelX"));
        myPanel.setLayoutY(myUtil.getIntProperty("sidePanelY"));
        myPanel.setMinWidth(myUtil.getIntProperty("sidePanelWidth"));
        myPanel.setMaxWidth(myUtil.getIntProperty("sidePanelWidth"));
        myPanel.setMinHeight(myUtil.getIntProperty("sidePanelHeight"));
        myPanel.setMaxHeight(myUtil.getIntProperty("sidePanelHeight"));
    }

    /**
     * Returns the JavaFX node representing the side menu
     *
     * @return the JavaFX node representing the side menu
     */
    DraggableTabPane getPanel() {
        return myPanel;
    }

}