package ui.scenes.editor.sidemenu;

import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ui.builder.UIBuilder;
import ui.scenes.editor.sidemenu.DraggableTabPane;
import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerMenuUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Pim Chuaylua, Harshil Garg
 */
public class EditorControls {

    private static Parent myRoot;
    private static ResourceBundle myResources;
    private static UIBuilder myBuilder;
    private static ItemSideMenu myItemMenu;
    private static PlayerSideMenu myPlayerMenu;

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
     * @param tabs
     * @return
     */
    private TabPane createSideTabPane(List<Tab> tabs) {
        TabPane sideTabs = new TabPane();
        sideTabs.setSide(Side.LEFT);
        sideTabs.getTabs().addAll(tabs);
        sideTabs.setLayoutX(1155);
        sideTabs.setLayoutY(100);
        sideTabs.setId("control-tabs");
        sideTabs.getSelectionModel().select(tabs.get(tabs.size() - 1)); // select close tab as default
        return sideTabs;
    }

    /**
     * Creates the side tabs that hold control panels
     *
     * @return a List of the created tabs
     */
    private List<Tab> createTabs() {
        List<Tab> tabs = new ArrayList<Tab>();
        tabs.add(createSideTab("Items", myItemMenu.getPanel()));
        tabs.add(createSideTab("Players", myPlayerMenu.getPanel()));
        tabs.add(createSideTab("Grid", myItemMenu.getPanel()));
        tabs.add(createSideTab("Close", null));
        return tabs;
    }

    /**
     * Initializes the side tabs that contain control panels for the editor
     */
    public void init() {
        myBuilder.addComponent(myRoot, createSideTabPane(createTabs()));
    }
}
