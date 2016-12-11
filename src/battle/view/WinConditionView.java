package battle.view;

import java.util.ResourceBundle;

import resources.properties.PropertiesUtilities;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Created by Bill Xiong on 12/8/16.
 * 
 * @author Bill Xiong
 */
public class WinConditionView {
	private Label label;
	private ResourceBundle resources;
	private PropertiesUtilities utilities;
	private static final String BATTLE_RESOURCES = "resources/properties/game-engine-battle";
	private ItemView itemView;
	private ImageView itemImageView;
	private ImageView backgroundImageView;
	private ImageView speechImageView;
	

	public WinConditionView(String condition,ItemView itemView) {
		label = new Label(condition);
		resources = ResourceBundle.getBundle(BATTLE_RESOURCES);
		utilities = new PropertiesUtilities(resources);
		this.itemView = itemView;
	}

	protected void addToGroup(Group group) {
		group.getChildren().clear();
		setUpView();
		group.getChildren().addAll(backgroundImageView,itemImageView,speechImageView,label);
	}
	
	private void setUpView() {

		int messageX = utilities.getIntProperty("messageX");
		int messageY = utilities.getIntProperty("messageY");
		int windowWidth = utilities.getIntProperty("windowWidth");
		int windowHeight = utilities.getIntProperty("windowHeight");
		int winningPlayerX = utilities.getIntProperty("winningPlayerX");
		int winningPlayerY = utilities.getIntProperty("winningPlayerY");
		int winningPlayerSize = utilities.getIntProperty("winningPlayerSize");
		int speechBubbleX = utilities.getIntProperty("speechBubbleX");
		int speechBubbleY = utilities.getIntProperty("speechBubbleY");
		int speechBubbleWidth = utilities.getIntProperty("speechBubbleWidth");
		int speechBubbleHeight = utilities.getIntProperty("speechBubbleHeight");
		
		//set background
		Image backgroundImage = new Image("resources/images/battles/background/background-1.jpg");
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(windowWidth);
        backgroundImageView.setFitHeight(windowHeight);
        backgroundImageView.setImage(backgroundImage);
        backgroundImageView.setLayoutX(0);
        backgroundImageView.setLayoutY(0);
        
		//set player image
        itemImageView = itemView.getView();
        itemImageView.setFitWidth(winningPlayerSize);
        itemImageView.setFitHeight(winningPlayerSize);
        itemImageView.setLayoutX(winningPlayerX);
        itemImageView.setLayoutY(winningPlayerY);
		
		//set speech bubble image
        Image speechImage = new Image("resources/images/battles/speech-bubble.png");
        speechImageView = new ImageView();
        speechImageView.setFitWidth(speechBubbleWidth);
        speechImageView.setFitHeight(speechBubbleHeight);
        speechImageView.setImage(speechImage);
        speechImageView.setImage(speechImage);
        speechImageView.setLayoutX(speechBubbleX);
        speechImageView.setLayoutY(speechBubbleY);
		
        label.setLayoutX(messageX);
        label.setLayoutY(messageY);
        label.setStyle("-fx-font-family: Pixeled; -fx-font-size: 20;");
	}
}
