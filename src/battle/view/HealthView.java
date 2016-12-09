package battle.view;


import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import resources.properties.PropertiesUtilities;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Display health of players on battle scene
 * @author Pim Chuaylua
 * */

public class HealthView {
	
	private Group root;
	private Button playerChart;
	private Label score;
	
	private ResourceBundle resources;
	private PropertiesUtilities utilities;
	private static final String BATTLE_RESOURCES = "resources/properties/game-engine-battle";
	
	HealthView(int x, int y) {
		root = new Group();
		resources = ResourceBundle.getBundle(BATTLE_RESOURCES);
		utilities = new PropertiesUtilities();
		initHealthBar(x,y);
	}
	
	private void initHealthBar(int x,int y) {
		int healthBoxWidth = utilities.getIntProperty(resources, "healthBoxWidth");
		int healthBoxHeight = utilities.getIntProperty(resources, "healthBoxHeight");
		
		Image image = new Image("resources/images/battles/square.png");
        ImageView squareView = new ImageView();
        squareView.setFitWidth(healthBoxWidth);
        squareView.setFitHeight(healthBoxHeight);
        squareView.setImage(image);
        squareView.setLayoutX(x);
        squareView.setLayoutY(y);
        
        playerChart = new Button();
        playerChart.getStyleClass().add("playerChart");
        playerChart.setPrefSize(100,5);
        playerChart.setLayoutX(x+10);
        playerChart.setLayoutY(y+10);
        
        score = new Label("HP: "+100);
        score.setLayoutX(x+150);
		score.setLayoutY(y+10);
        
        root.getChildren().addAll(squareView,playerChart,score);
	}

	public Group getGroup() {
		return root;
	}
	
	public void update(ItemView itemView) {
		playerChart.setPrefSize(itemView.getHP(),5);
		score.setText("HP: "+itemView.getHP());
	}
	
}
