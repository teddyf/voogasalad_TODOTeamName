package ui.scenes.engine;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.media.SoundChooser;
import ui.media.SoundPlayer;

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
    private EngineView gameEngine;

    public EngineSidePanel(Parent root, UIBuilder builder, ResourceBundle resources,Character player,EngineView gameEngine) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        util = new PropertiesUtilities(myResources);
        vbox = new VBox(10);
        this.gameEngine = gameEngine;
        Font.loadFont(EngineSidePanel.class.getResource("/resources/fonts/PokemonGB.ttf").toExternalForm(), 20);
        initSidePanel();
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
        
        SoundPlayer soundPlayer= new SoundPlayer("src/resources/songs/aquacorde.mp3");
        soundPlayer.addNodeToControl(new SnapShot(gameEngine).getGroup());
        vbox.getChildren().add(soundPlayer.getGroup());
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