package battle.view;
import java.util.Observable;
import java.util.Observer;

import battle.controller.BattleModelInView;
import battle.model.Difficulty;
import block.EnemyBlock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * @author Daniel Chai, Bill Xiong
 * This code is well designed because there are no magic values, and also this class
 * implements the Observable Design pattern, where the BattleView observes the BattleModel
 * for updates. This reduces extraneous code and makes it clean and easy to read.
 */
public class BattleView implements Observer {
	/*CONSTANTS*/
	private static final String CSS_FILE_PATH = "resources/styles/game-engine.css";
	private static final String ENEMY_IMAGE_PATH = "resources/images/battles/pokemon-1.gif";
	private static final String PLAYER_IMAGE_PATH = "resources/images/battles/pokemon-2.gif";

	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private final int DISPLAY_X = 500;
	private final int DISPLAY_Y = 250;
	private final int PLAYER_X = 50;
	private final int PLAYER_Y = 250;
	private final int ENEMY_X = 250;
	private final int ENEMY_Y = 250;
	private final int OFFSET = 30;
	private final int ENEMY_OFFSET = 100 ;
	private final String ATTACK = "ATTACK";
	private final String NUMPOKE = "Number of Pokemon";
	private final String FONT = "Pokemon GB";
	private final String BATTLES_WON = "Battles Won: ";
	private final String BATTLES_LOST = "Battles Lost: ";
	private final String WON = "You won";
	private final String LOST = "You lost";
	private BattleModelInView model;

	private Difficulty gameDifficulty;
	private Scene scene;
	private Group root;
	private ImageView backgroundView;
	private EnemyView enemy;
	private PlayerView player;
	private BattleButton reduceHP;
	private BattleButton shield;
	private HealthDisplay enemyHealth;
	private HealthDisplay playerHealth;
	private Label displayPokemon;
    private boolean usingShield;

	public BattleView(Difficulty diff, String backgroundFilePath) {
		usingShield = false;
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT);
		root.getStylesheets().add(CSS_FILE_PATH);
		gameDifficulty = diff;
		setBackground(backgroundFilePath);
		displayPokemon = new Label();
		addButtons(DISPLAY_X, DISPLAY_Y, "Attack");
	}
	
	public void setModel(BattleModelInView modelInView) {
		this.model = modelInView;

		enemy = new EnemyView(model.getEnemyHP(), ENEMY_X, ENEMY_Y, ENEMY_IMAGE_PATH);
		player = new PlayerView(model.getPlayerHP(), PLAYER_X, PLAYER_Y, PLAYER_IMAGE_PATH);

		enemyHealth = new HealthDisplay(ENEMY_X + OFFSET, ENEMY_Y -ENEMY_OFFSET, (int)model.getEnemyHP());
		playerHealth = new HealthDisplay(PLAYER_X - OFFSET, PLAYER_Y -ENEMY_OFFSET, (int)model.getPlayerHP());
		root.getChildren().addAll(enemyHealth.getGroup(), playerHealth.getGroup());
		enemy.addToGroup(root);
		player.addToGroup(root);

		displayBattleStats();
		displayNumPokemon();
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
		shield = new BattleButton("Shield", x, y + ENEMY_OFFSET);
		shield.addToGroup(root);
		addShieldHandler();
		addReduceHandler();
	}

	private void displayNumPokemon() {
		displayPokemon.setText(NUMPOKE + model.getNumPokemon());
		displayPokemon.setLayoutX(DISPLAY_X);
		displayPokemon.setLayoutY(DISPLAY_Y);
		root.getChildren().add(displayPokemon);
	}
	private void displayBattleStats(){
		Label l = new Label(BATTLES_WON + model.battlesWon() + BATTLES_LOST + model.battlesLost());
        l.setLayoutX(DISPLAY_X);
		l.setLayoutY(10);
		l.setFont(new Font(FONT, 10));
		root.getChildren().add(l);
	}

	public void displayTextPokemon() {
		displayPokemon.setText(NUMPOKE+ model.getNumPokemon());
		displayPokemon.setFont(new Font(FONT, 10));
	}
	private void addShieldHandler(){
		EventHandler<ActionEvent> event = actionEvent -> {
        	usingShield = true;
		};
		shield.addHandler(event);
	}
	private void addReduceHandler() {
		EventHandler<ActionEvent> event = actionEvent -> {
            System.out.println(usingShield);
            FireBall f = new FireBall(root);
		    f.throwFireBall(PLAYER_X,PLAYER_Y,ENEMY_X);
			if (!(model.checkPlayerLost() || model.checkPlayerWon())) {
				model.setEnemyHP(model.getEnemyHP()
						- (Math.random() * 1.45) * EnemyBlock.DEFAULT_HEALTH / gameDifficulty.getValue());
				if(!usingShield) {
					model.setPlayerHP(model.getPlayerHP() - (Math.random()) * gameDifficulty.getValue() / 3.3);
				}
				usingShield = false;
			}
		};
		reduceHP.addHandler(event);
	}

	@Override
	public void update(Observable o, Object arg) {
		player.setHP((int) model.getPlayerHP());
		enemy.setHP((int) model.getEnemyHP());

		System.out.println(model.getNumPokemon());
		if (model.checkPlayerLost()) {
			lose();
		}
		if (model.checkPlayerWon()) {
			win();
		}
		displayTextPokemon();
		enemyHealth.update(enemy);
		playerHealth.update(player);
	}

	private void win() {
		model.addBattleWon();
		WinConditionView won = new WinConditionView(WON, player);
		won.addToGroup(root);
	}

	private void lose() {
		model.reduceNumPokemon();
		if (model.getNumPokemon() <= 0) {
			model.addBattleLost();
			WinConditionView lost = new WinConditionView(LOST, enemy);
			lost.addToGroup(root);
		} else {
			model.resetPlayer();
		}
	}
}
