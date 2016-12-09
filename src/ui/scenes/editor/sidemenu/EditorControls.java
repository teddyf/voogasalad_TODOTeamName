package ui.scenes.editor.sidemenu;

import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ui.builder.UIBuilder;
import ui.scenes.editor.sidemenu.DraggableTabPane;
import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerMenuUI;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Pim Chuaylua, Harshil Garg
 */
public class EditorControls {

    private Parent myRoot;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private SideMenu myItemMenu;
    private SideMenu myPlayerMenu;
    private SideMenu myCustomMenu;

    public EditorControls(Parent root, ResourceBundle resources, ItemSideMenu itemMenu, PlayerSideMenu playerMenu) {
        myRoot = root;
        myResources = resources;
        myBuilder = new UIBuilder();
        myItemMenu = itemMenu;
        myPlayerMenu = playerMenu;
        myCustomMenu = new CustomSideMenu(myRoot, myResources);
    }



    public void addEditorControls() {

        TabPane sideTabs = new TabPane();

        Tab itemTab = new Tab();
        itemTab.setText("Items");
        itemTab.setClosable(false);
        itemTab.setOnSelectionChanged(e -> {
            if (itemTab.isSelected()) {
                myBuilder.addComponent(myRoot, myItemMenu.getPanel());
            } else {
                myBuilder.removeComponent(myRoot, myItemMenu.getPanel());
            }
        });

        Tab playerTab = new Tab();
        playerTab.setText("Players");
        playerTab.setClosable(false);
        playerTab.setOnSelectionChanged(e -> {
            if (playerTab.isSelected()) {
                myBuilder.addComponent(myRoot, myPlayerMenu.getPanel());
            } else {
                myBuilder.removeComponent(myRoot, myPlayerMenu.getPanel());
            }
        });

        Tab customTab = new Tab();
        customTab.setText("Custom");
        customTab.setClosable(false);
        customTab.setOnSelectionChanged(e -> {
            if (customTab.isSelected()) {
                myBuilder.addComponent(myRoot, myCustomMenu.getPanel());
            } else {
                myBuilder.removeComponent(myRoot, myCustomMenu.getPanel());
            }
        });

        sideTabs.setSide(Side.LEFT);
        sideTabs.getTabs().addAll(itemTab, playerTab, customTab);
        sideTabs.setLayoutY(100);
        sideTabs.setLayoutX(1155);
        sideTabs.setId("control-tabs");
        sideTabs.getSelectionModel().select(playerTab);

        myBuilder.addComponent(myRoot, sideTabs);

    }



}
