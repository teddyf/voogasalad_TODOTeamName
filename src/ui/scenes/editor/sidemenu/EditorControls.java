package ui.scenes.editor.sidemenu;

import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.scenes.editor.sidemenu.DraggableTabPane;
import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerMenuUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Pim Chuaylua, Harshil Garg
 *         <p>
 *         This class creates the side tabs that each hold a control panel
 *         for controlling aspects of the editor.
 */
public class EditorControls {

    private Parent myRoot;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private SideMenu myItemMenu;
    private SideMenu myPlayerMenu;

    public EditorControls(Parent root, ResourceBundle resources, ItemSideMenu itemMenu) {
        myRoot = root;
        myResources = resources;
        myBuilder = new UIBuilder();
        myItemMenu = itemMenu;
        myPlayerMenu = new PlayerSideMenu(myRoot, myResources);
        init();
    }

    /**
     * Creates a side tab and ties content to it
     *
     * @param title   is the title of the tab
     * @param content is the content attached to the tabs
     * @return the newly created tab
     */
    private Tab createSideTab(String title, TabPane content) {
        Tab newTab = new Tab();
        newTab.setText(title);
        newTab.setClosable(false);
        if (content != null) {
            newTab.setOnSelectionChanged(e -> {
                if (newTab.isSelected()) {
                    myBuilder.addComponent(myRoot, content);
                } else {
                    myBuilder.removeComponent(myRoot, content);
                }
            });
        }
        return newTab;
    }

    /**
     * Creates a left-oriented TabPane to be placed on the right side of the screen
     *
     * @param tabs are the tabs to add to the pane
     * @return the created TabPane, populated with its tabs
     */
    private TabPane createSideTabPane(List<Tab> tabs) {
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        TabPane sideTabs = new TabPane();
        sideTabs.setSide(Side.LEFT);
        sideTabs.getTabs().addAll(tabs);
        sideTabs.setLayoutX(util.getIntProperty("sideTabsX"));
        sideTabs.setLayoutY(util.getIntProperty("sideTabsY"));
        sideTabs.setId(myResources.getString("sideTabsCSSid"));
        sideTabs.getSelectionModel().select(tabs.get(tabs.size() - 1)); // select close tab as default
        return sideTabs;
    }

    /**
     * Creates the side tabs that hold control panels
     *
     * @return a List of the created tabs
     */
    private List<Tab> createTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(createSideTab(myResources.getString("firstTab"), myItemMenu.getPanel()));
        tabs.add(createSideTab(myResources.getString("secondTab"), myPlayerMenu.getPanel()));
        tabs.add(createSideTab(myResources.getString("thirdTab"), myItemMenu.getPanel()));
        tabs.add(createSideTab(myResources.getString("fourthTab"), null));
        return tabs;
    }

    /**
     * Initializes the side tabs that contain control panels for the editor
     */
    public void init() {
        myBuilder.addComponent(myRoot, createSideTabPane(createTabs()));
    }
}