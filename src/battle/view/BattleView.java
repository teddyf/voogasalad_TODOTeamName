package battle.view;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import battle.model.*;
import battle.controller.BattleModelInView;
import battle.WinConditionView;
import block.EnemyBlock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView implements Observer {
	BattleModelInView model;

	public enum Difficulty{
		EASY, MEDIUM, HARD
	}
	private static final int EASY = 10;
	private static final int MEDIUM= 18;
	private static final int HARD = 25;
	private static final HashMap<Difficulty, Integer> difficulties = new HashMap<Difficulty,Integer>() {{
		put(Difficulty.EASY, EASY);
		put(Difficulty.MEDIUM, MEDIUM);
		put(Difficulty.HARD, HARD);
	}};

	private Difficulty gameDifficulty;

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 500;
	// protected static final int OFFSET = 40;
    // protected static final int OFFSET_Y = 20;
	// protected static final int RECTANGLE_WIDTH = 40;
	// protected static final int RECTANGLE_HEIGHT = 40;
	// protected static final Color BACKGROUND = Color.AZURE;
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
	
	private static final String CSS_FILE_NAME = "resources/styles/game-engine.css";

	public BattleView(Difficulty diff, String backgroundFilePath) {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT);
		root.getStylesheets().add(CSS_FILE_NAME);
		gameDifficulty = diff;
		setBackground(backgroundFilePath);
		addButtons(500, 200, "Reduce HP by 10");
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
	private void addReduceHandler(){
		EventHandler<ActionEvent> event = actionEvent -> {
            if(!(model.checkPlayerLost() || model.checkPlayerWon())) {
                model.setEnemyHP(model.getEnemyHP() - (Math.random() * 1.45) * EnemyBlock.DEFAULT_HEALTH/difficulties.get(gameDifficulty));
                model.setPlayerHP(model.getPlayerHP() - (Math.random()) * difficulties.get(gameDifficulty)/3.3);
            }
        };
		reduceHP.addHandler(event);
	}


	@Override
	public void update(Observable o, Object arg) {
		BattleModel model = (BattleModel) o;
		player.setHP(model.getPlayerHP());
		enemy.setHP(model.getEnemyHP());
		
		if (model.checkPlayerLost()) {
            lose(model);
		}
		if (model.checkPlayerWon()) {
			win(model);
		}
		
		enemyHealth.update(enemy);
		playerHealth.update(player);
	}

	public void setModel(BattleModelInView model) {
		this.model = model;
		
		enemy = new EnemyView(model.getEnemyHP(), ENEMY_X, ENEMY_Y,"resources/images/battles/pokemon-1.gif");
		player = new PlayerView(model.getPlayerHP(), PLAYER_X, PLAYER_Y,"resources/images/battles/pokemon-2.gif");
		
		enemyHealth = new HealthDisplay(ENEMY_X+50,ENEMY_Y+200);
		playerHealth = new HealthDisplay(PLAYER_X-50,PLAYER_Y+200);
		
		root.getChildren().addAll(enemyHealth.getGroup(),playerHealth.getGroup());
		enemy.addToGroup(root);
		player.addToGroup(root);
	}
	private void win(BattleModel model){
		model.addBattleWon();
		WinConditionView won = new WinConditionView("You won");
		won.addToGroup(root);
	}
	private void lose(BattleModel model){
		model.addBattleLost();
		WinConditionView lost = new WinConditionView("You lost");
		lost.addToGroup(root);
	}
}
