package battle;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView implements Observer {

	protected static final int WIDTH = 500;
	protected static final int HEIGHT = 500;
	protected static final int OFFSET = 40;
	protected static final int RECTANGLE_WIDTH = 40;
	protected static final int RECTANGLE_HEIGHT = 40;
	protected static final Color BACKGROUND = Color.AZURE;
	private final int PLAYER_X = BattleView.WIDTH / 2;
	private final int PLAYER_Y = 50;
	private final int ENEMY_X = BattleView.HEIGHT / 2;
	private final int ENEMY_Y = 200;
	private final int BUTTON_Y = 400;
    protected static final int DAMAGE = 10;
	private Scene scene;
	private Group root;


	private EnemyView enemy;
	private PlayerView player;
    private Button reduceHP;
	public BattleView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		enemy = new EnemyView(100, ENEMY_X, ENEMY_Y);
		player = new PlayerView(100, PLAYER_X, PLAYER_Y);
        reduceHP = new Button();
		addButtons(50, BUTTON_Y, "Reduce HP by 10");
        addPlayers();
        addHandlers();
	}

	public Scene getScene() {
		return scene;
	}

	private void addPlayers() {
		enemy.addItems(root);
		player.addItems(root);
	}

	private void addButtons(int x, int y, String text) {
        reduceHP.setText(text);
		reduceHP.setLayoutX(x);
		reduceHP.setLayoutY(y);
		root.getChildren().add(reduceHP);
	}
	protected void addHandlers(){
        reduceHP.setOnAction(actionEvent -> {
            //TODO add update here
            int d = enemy.getHP() - DAMAGE;
            enemy.setHP(d);
        });
    }

	@Override
	public void update(Observable o, Object arg) {
		BattleModel model = (BattleModel) o;
		player.setHP(model.getPlayerHP());
		enemy.setHP(model.getEnemyHP());
	}
}
