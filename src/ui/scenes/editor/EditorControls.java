package ui.scenes.editor;

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

    private static Parent myRoot;
    private static ResourceBundle myResources;
    private static UIBuilder myBuilder;
    private static ItemSideMenu myItemMenu;
    private static PlayerMenuUI myPlayerMenu;

    EditorControls(Parent root, ResourceBundle resources, ItemSideMenu itemMenu, PlayerMenuUI playerMenu) {
        myRoot = root;
        myResources = resources;
        myBuilder = new UIBuilder();
        myItemMenu = itemMenu;
        myPlayerMenu = playerMenu;
    }



    public void addEditorControls() {

        TabPane sideTabs = new TabPane();

        Tab itemTab = new Tab();
        itemTab.setText("Items");
        itemTab.setClosable(false);
        itemTab.setOnSelectionChanged(e -> {
            if (itemTab.isSelected()) {
                myBuilder.addComponent(myRoot, myItemMenu.getItemPanel());
            } else {
                myBuilder.removeComponent(myRoot, myItemMenu.getItemPanel());
            }
        });

        Tab playerTab = new Tab();
        playerTab.setText("Players");
        playerTab.setClosable(false);
        playerTab.setOnSelectionChanged(e -> {
            if (playerTab.isSelected()) {
                myBuilder.addComponent(myRoot, myPlayerMenu.getPlayerPanel());
            } else {
                myBuilder.removeComponent(myRoot, myPlayerMenu.getPlayerPanel());
            }
        });

        Tab customTab = new Tab();
        customTab.setText("Custom");
        customTab.setClosable(false);
        customTab.setOnSelectionChanged(e -> {
            if (customTab.isSelected()) {
                myBuilder.addComponent(myRoot, new DraggableTabPane());
            } else {
                myBuilder.removeComponent(myRoot, new DraggableTabPane());
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
