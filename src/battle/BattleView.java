package battle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Color BACKGROUND = Color.AZURE;
	
	private Scene scene;
	private Group root;
	
	public BattleView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		
		addButtons();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	private void addButtons() {
		Button enemyButton = new Button();
		enemyButton.setLayoutX(50);
		enemyButton.setLayoutY(50);
		enemyButton.setText("Enemy");
		
		Button playerButton = new Button();
		playerButton.setLayoutX(50);
		playerButton.setLayoutY(150);
		playerButton.setText("Player");
		
		root.getChildren().add(enemyButton);
		root.getChildren().add(playerButton);
	}
}
