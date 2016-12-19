package view.battle;

/*
 * This class shows the HP chart of the player and enemy on the battle scene UI.
 * @author pim
 */

//This is my masterpiece - Pim Chuaylua

import java.util.ResourceBundle;

import utilities.PropertiesUtilities;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class HealthView {

	private Group root;
	private Button playerChart;
	private Label score;
	private ResourceBundle resources;
	private PropertiesUtilities utilities;
	private static final String BATTLE_RESOURCES = "resources/properties/game-engine-battle";

	HealthView(int x, int y, int hp) {
		initHealthBar(x, y, hp);
		resources = ResourceBundle.getBundle(BATTLE_RESOURCES);
		utilities = new PropertiesUtilities(resources);
	}

	private void initHealthBar(int x, int y, int hp) {
		
		int healthBoxWidth = utilities.getIntProperty("healthBoxWidth");
		int healthBoxHeight = utilities.getIntProperty("healthBoxHeight");
		String healthBoxImage = utilities.getStringProperty("healthBoxImage");
		String hpText = utilities.getStringProperty("hpText");
		
		root = new Group();
		Image image = new Image(healthBoxImage);
		ImageView squareView = new ImageView();
		squareView.setFitWidth(healthBoxWidth);
		squareView.setFitHeight(healthBoxHeight);
		squareView.setImage(image);
		squareView.setLayoutX(x);
		squareView.setLayoutY(y);

		playerChart = new Button();
		playerChart.getStyleClass().add("playerChart");
		playerChart.setPrefSize(hp, 2);
		playerChart.setLayoutX(x + 10);
		playerChart.setLayoutY(y + 10);
		
		score = new Label(hpText + hp);
		score.setLayoutX(x + 150);
		score.setLayoutY(y + 10);

		root.getChildren().addAll(squareView, playerChart, score);
	}

	public Group getGroup() {
		return root;
	}

	public void update(ItemView itemView) {
		String hpText = utilities.getStringProperty("hpText");
		playerChart.setPrefSize(itemView.getHP(), 2);
		score.setText(hpText + itemView.getHP());
		score.setFont(new Font("Pokemon GB",10));
	}

}
