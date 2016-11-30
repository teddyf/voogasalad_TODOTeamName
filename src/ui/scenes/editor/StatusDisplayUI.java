package ui.scenes.editor;

import editor.EditorController;
import editor.EditorSidePanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ObjectMenuObjects.GameObjects;

/**
 * @author Pim Chuaylua
 *         <p>
 *         This class initializes player status ui.
 */
public class StatusDisplayUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private PropertiesUtilities util = new PropertiesUtilities();

    public StatusDisplayUI(Parent root, UIBuilder builder, ResourceBundle resources) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        vbox = new VBox(10);
    }

    /**
     * Creates the tab-based menu that will hold the objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    public void initPlayerMenu() {
    	
    	int itemMenuXPos = util.getIntProperty(myResources, "statusPanelPosX");
        int itemMenuYPos = util.getIntProperty(myResources, "statusPanelPosY");
        
        Group itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);
        
        vbox.setPadding(new Insets(10, 10, 10, 10));
        
        
        vbox.getChildren().add(new Label("Status"));
        vbox.getChildren().add(new Label("Status"));
    }
    
}