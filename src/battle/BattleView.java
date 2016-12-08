package battle;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView implements Observer {
	BattleModelInView model;

	protected static final int WIDTH = 500;
	protected static final int HEIGHT = 500;
	protected static final int OFFSET = 40;
	protected static final int RECTANGLE_WIDTH = 40;
	protected static final int RECTANGLE_HEIGHT = 40;
	protected static final Color BACKGROUND = Color.AZURE;
	protected static final int DAMAGE = 10;
	
	private final int PLAYER_X = BattleView.WIDTH / 2;
	private final int PLAYER_Y = 50;
	private final int ENEMY_X = BattleView.HEIGHT / 2;
	private final int ENEMY_Y = 200;
	private final int BUTTON_Y = 400;
	
	private Scene scene;
	private Group root;

	private EnemyView enemy;
	private PlayerView player;
	private Button reduceHP;

	public BattleView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		
		addButtons(50, BUTTON_Y, "Reduce HP by 10");	
	}

	public Scene getScene() {
		return scene;
	}

	private void addButtons(int x, int y, String text) {
		reduceHP = new Button();
		reduceHP.setText(text);
		reduceHP.setLayoutX(x);
		reduceHP.setLayoutY(y);
		root.getChildren().add(reduceHP);
	}

	protected void addHandlers() {
		reduceHP.setOnAction(actionEvent -> {
            if(!(model.checkPlayerLost() || model.checkPlayerWon()))
			    model.setEnemyHP(model.getEnemyHP() - DAMAGE);
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		BattleModel model = (BattleModel) o;
		player.setHP(model.getPlayerHP());
		enemy.setHP(model.getEnemyHP());
		
		if (model.checkPlayerLost()) {
            WinCondition lost = new WinCondition("you lost");
            lost.addWinCondition(root);
			System.out.println("player lost");
		}
		if (model.checkPlayerWon()) {
            WinCondition won = new WinCondition("You won");
            won.addWinCondition(root);
			System.out.println("player won");
		}
	}

	protected void setModel(BattleModelInView model) {
		this.model = model;
		
		enemy = new EnemyView(model.getEnemyHP(), ENEMY_X, ENEMY_Y);
		player = new PlayerView(model.getPlayerHP(), PLAYER_X, PLAYER_Y);
		
		enemy.addToGroup(root);
		player.addToGroup(root);
		
		addHandlers();
	}
}
