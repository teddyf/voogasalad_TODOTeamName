package ui.scenes.engine;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Pim Chuaylua
 *         <p>
 *         This class initializes player status ui.
 */
public class StatsDisplayUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private PropertiesUtilities util = new PropertiesUtilities();

    public StatsDisplayUI(Parent root, UIBuilder builder, ResourceBundle resources) {
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
    public void initSideMenu() {
    	
    	int itemMenuXPos = util.getIntProperty(myResources, "statusPanelPosX");
        int itemMenuYPos = util.getIntProperty(myResources, "statusPanelPosY");
        
        Pane itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: grey;");
        canvas.setPrefSize(300,1000);
        itemMenuRegion.getChildren().add(canvas);
        
        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        
        vbox.getChildren().add(new Label("User Stats"));  
    }
    
    public void initPlayerChanger(PlayerUI player) {
    	vbox.getChildren().add(new PlayerChanger(player).getGroup());
    }
    
}