package ui.scenes.editor;

import editor.SidePanel;
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
 *         This class initializes player menu on game editor ui.
 */
public class PlayerMenuUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private ArrayList<String> playerImagePathList = new ArrayList<String>();
    private PropertiesUtilities util = new PropertiesUtilities();
    private String chosenImageFilePath;

    PlayerMenuUI(Parent root, UIBuilder builder, ResourceBundle resources) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        vbox = new VBox();
    }

    /**
     * Creates the tab-based menu that will hold the objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    public void initPlayerMenu() {
    	
    	int itemMenuXPos = util.getIntProperty(myResources, "playerMenuXPos");
        int itemMenuYPos = util.getIntProperty(myResources, "playerMenuYPos");
        
        Group itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);
        
        vbox.getChildren().add(new Label("Choose Player"));
        
        setPlayerImagePathList();
        setPlayerOptionsMenu();  
    }
    
    private void setPlayerOptionsMenu() {
    	
    	FlowPane playerOptions = new FlowPane();
    	playerOptions.setPrefWrapLength(300);
        playerOptions.setHgap(20);
        playerOptions.setVgap(20);
        playerOptions.setPadding(new Insets(20, 20, 20, 20));
        
    	int playerImageSize = util.getIntProperty(myResources, "playerImageSize");
    	
    	for (String path:playerImagePathList) {
    		ImageView playerOne = new ImageView(new Image(path));
            playerOne.setFitHeight(playerImageSize);
    		playerOne.setFitWidth(playerImageSize); 
    		Button button= new Button();
    		button.setGraphic(playerOne);
    		
    		button.setOnAction(new EventHandler<ActionEvent>() {
    		    @Override public void handle(ActionEvent e) {
    		    	chosenImageFilePath = path;
    		    }
    		});
    	
    		playerOptions.getChildren().add(button);
    	}
    	
    	vbox.getChildren().add(playerOptions); 
    }  
    
    private void setPlayerImagePathList() {
    	playerImagePathList.add("resources/images/Sprites/Character/Pokemon/default.png");
    	playerImagePathList.add("resources/images/Sprites/Character/Pokemon/default.png");
    	playerImagePathList.add("resources/images/Sprites/Character/Pokemon/default.png");
    }
    
    public String getChosenImageFilePath() {
    	return chosenImageFilePath;
    }
    
}