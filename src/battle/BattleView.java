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

	protected static final int WIDTH = 500;
	protected static final int HEIGHT = 500;
    protected static final int OFFSET = 40;
    protected static final int RECTANGLE_WIDTH = 40;
    protected static final int RECTANGLE_HEIGHT = 40;
    protected static final Color BACKGROUND = Color.AZURE;
    private final int PLAYER_X = BattleView.WIDTH/2;
    private final int PLAYER_Y = 50;
    private final int ENEMY_X = BattleView.HEIGHT/2;
    private final int ENEMY_Y = 200;
    private final int BUTTON_Y = 400;

	private Scene scene;
	private Group root;

    private EnemyView enemy;
    private PlayerView player;

	public BattleView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
        enemy = new EnemyView(100, ENEMY_X, ENEMY_Y);
        player = new PlayerView(100, PLAYER_X, PLAYER_Y);
		addButtons(50, BUTTON_Y, "Reduce HP by 10");
		addButtons(250, BUTTON_Y, "Reduce HP by 20");
		addPlayers();
	}

	public Scene getScene() {
		return scene;
	}
	private void addPlayers(){
        enemy.addItems(root);
        player.addItems(root);
    }
	private void addButtons(int x, int y, String text){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        root.getChildren().add(button);
    }
}
