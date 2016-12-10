package ui.scenes.engine;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.media.SoundControl;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * @author Pim Chuaylua
 *         <p>
 *         This class initializes player status ui.
 */
public class EngineSidePanel implements Observer {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private PropertiesUtilities util;
    private Character player;

    public EngineSidePanel(Parent root, UIBuilder builder, ResourceBundle resources,Character player) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        util = new PropertiesUtilities(myResources);
        vbox = new VBox(10);
        Font.loadFont(EngineSidePanel.class.getResource("/resources/fonts/Pixeled.ttf").toExternalForm(), 10);
        initSidePanel();
        initPlayerChanger(player);
        initStats();
    }

    /**
     * Creates the tab-based menu that will hold the ui.scenes.editor.objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    public void initSidePanel() {

    	int itemMenuXPos = util.getIntProperty("statusPanelPosX");
        int itemMenuYPos = util.getIntProperty("statusPanelPosY");
        
        Pane itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        Pane canvas = new Pane(); 
        canvas.getStyleClass().add("canvas");
        canvas.setPrefSize(300,1000);
        itemMenuRegion.getChildren().add(canvas);
        
        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);
        
        vbox.setPadding(new Insets(10, 10, 10, 10));  
        
        vbox.getChildren().add(new SoundControl().getGroup());
    }
    
    public void initPlayerChanger(Character player) {
    	vbox.getChildren().add(new CharacterChanger(player,myBuilder,myRoot).getGroup());
    }
    
    public void initStats() {
    	vbox.getChildren().add(new Label("Your HP"));  
        Button playerChart = new Button();
        playerChart.getStyleClass().add("playerChart");
        playerChart.setPrefSize(100,10);
        vbox.getChildren().add(playerChart);
        
        vbox.getChildren().add(new Label("Battle History"));  
        
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}  
}