package battle.view;

import java.util.Observable;
import java.util.Observer;

import battle.controller.BattleModelInView;
import battle.model.Difficulty;
import battle.view.WinConditionView;
import block.EnemyBlock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView implements Observer {
	//private static final String CSS_FILE_PATH = "resources/styles/game-engine.css";
	private static final String ENEMY_IMAGE_PATH = "resources/images/battles/pokemon-1.gif";
	private static final String PLAYER_IMAGE_PATH = "resources/images/battles/pokemon-2.gif";
	
	BattleModelInView model;

	private Difficulty gameDifficulty;

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 500;
	public static final int DAMAGE = 10;

	private final int PLAYER_X = 300;
	private final int PLAYER_Y = 200;
	private final int ENEMY_X = 500;
	private final int ENEMY_Y = 200;

	private Scene scene;
	private Group root;
	private ImageView backgroundView;
	private EnemyView enemy;
	private PlayerView player;
	private BattleButton reduceHP;
	private HealthDisplay enemyHealth;
	private HealthDisplay playerHealth;
	private HPWarning lowWarning;

	public BattleView(Difficulty diff, String backgroundFilePath) {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT);
		//root.getStylesheets().add(CSS_FILE_PATH);
		gameDifficulty = diff;
		setBackground(backgroundFilePath);
		addButtons(500, 200, "Reduce HP by 10");
		lowWarning = new HPWarning();
	}

	private void setBackground(String filePath) {
		Image image = new Image(filePath);
		backgroundView = new ImageView();
		backgroundView.setFitWidth(WIDTH);
		backgroundView.setFitHeight(HEIGHT);
		backgroundView.setImage(image);
		backgroundView.setLayoutX(0);
		backgroundView.setLayoutY(0);
		root.getChildren().addAll(backgroundView);
	}

	public Scene getScene() {
		return scene;
	}

	private void addButtons(int x, int y, String text) {
		reduceHP = new BattleButton(text, x, y);
		reduceHP.addToGroup(root);
		addReduceHandler();
	}

	private void addReduceHandler() {
		EventHandler<ActionEvent> event = actionEvent -> {
			if (!(model.checkPlayerLost() || model.checkPlayerWon())) {
				model.setEnemyHP(model.getEnemyHP()
						- (Math.random() * 1.45) * EnemyBlock.DEFAULT_HEALTH / gameDifficulty.getValue());
				model.setPlayerHP(model.getPlayerHP() - (Math.random()) * gameDifficulty.getValue() / 3.3);
			}
		};
		reduceHP.addHandler(event);
	}

	@Override
	public void update(Observable o, Object arg) {
		player.setHP((int) model.getPlayerHP());
		enemy.setHP((int) model.getEnemyHP());

		if (model.getPlayerHP() < 20) {
			lowWarning.showAlert();
		}
		
		if (model.checkPlayerLost()) {
			lose();
		}
		if (model.checkPlayerWon()) {
			win();
		}

		enemyHealth.update(enemy);
		playerHealth.update(player);
	}

	public void setModel(BattleModelInView modelInView) {
		this.model = modelInView;

		enemy = new EnemyView(model.getEnemyHP(), ENEMY_X, ENEMY_Y, ENEMY_IMAGE_PATH);
		player = new PlayerView(model.getPlayerHP(), PLAYER_X, PLAYER_Y, PLAYER_IMAGE_PATH);

		enemyHealth = new HealthDisplay(ENEMY_X + 50, ENEMY_Y + 200);
		playerHealth = new HealthDisplay(PLAYER_X - 50, PLAYER_Y + 200);

		root.getChildren().addAll(enemyHealth.getGroup(), playerHealth.getGroup());
		enemy.addToGroup(root);
		player.addToGroup(root);
	}

	private void win() {
		model.addBattleWon();
		WinConditionView won = new WinConditionView("You won",player);
		won.addToGroup(root);
	}

	private void lose() {
		model.addBattleLost();
		WinConditionView lost = new WinConditionView("You lost",enemy);
		lost.addToGroup(root);
	}
}
