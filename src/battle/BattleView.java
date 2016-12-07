package battle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Color BACKGROUND = Color.AZURE;
	private final int PLAYER_X = WIDTH/2;
    private final int PLAYER_Y = 50;
    private final int ENEMY_X = WIDTH/2;
    private final int ENEMY_Y = HEIGHT/2 + 100;
    private final int OFFSET = 40;
    private final int BUTTON_Y = 400;

	private Scene scene;
	private Group root;
    private Rectangle playerView, enemyView;
	private Label playerHP, enemyHP;
	public BattleView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		addButtons(50, BUTTON_Y, "Reduce HP by 10");
        addButtons(250, BUTTON_Y, "Reduce HP by 20");
        addPlayers();
        addLabels();
	}
	
	public Scene getScene() {
		return scene;
	}
	public void setPlayerHP(int hp){
        playerHP.setText("HP: " + hp);
    }
    public void setEnemyHP(int hp){
        enemyHP.setText("HP: " + hp);
    }
	private void addLabels(){
        playerHP = new Label("HP: " + 100);
        enemyHP = new Label("HP: " + 100);
        playerHP.setLayoutX(PLAYER_X + OFFSET);
        playerHP.setLayoutY(PLAYER_Y);
        enemyHP.setLayoutX(ENEMY_X + OFFSET);
        enemyHP.setLayoutY(ENEMY_Y);
        root.getChildren().add(playerHP);
        root.getChildren().add(enemyHP);
    }
	private void addPlayers(){
        playerView = new Rectangle(40, 40);
        enemyView = new Rectangle(40, 40);
        playerView.setLayoutX(PLAYER_X);
        playerView.setLayoutY(PLAYER_Y);
        enemyView.setLayoutX(ENEMY_X);
        enemyView.setLayoutY(ENEMY_Y);
        root.getChildren().add(playerView);
        root.getChildren().add(enemyView);
    }
	private void addButtons(int x, int y, String text){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        root.getChildren().add(button);
    }
}
