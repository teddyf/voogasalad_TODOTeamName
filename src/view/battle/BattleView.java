package view.battle;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import utilities.PropertiesUtilities;
import controller.battle.BattleModelInView;
import model.battle.Difficulty;
import model.block.blocktypes.EnemyBlock;
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
 */

//This entire file is part of my masterpiece.
//Pim Chuaylua

public class BattleView implements Observer {
	private ResourceBundle resources;
	private PropertiesUtilities utilities;
	private static final String CSS_FILE_PATH = "resources/styles/game-engine.css";
	private static final String BATTLE_RESOURCES = "resources/properties/game-engine-battle";

	private BattleModelInView model;

	private Difficulty gameDifficulty;

	private int width;
	private int height;
	private int displayX;
	private int displayY;
	private int playerX;
	private int playerY;
	private int enemyX;
	private int enemyY;

	private Scene scene;
	private Group root;
	private ImageView backgroundView;
	private EnemyView enemy;
	private PlayerView player;
	private BattleButton reduceHP;
	private BattleButton shield;
	private HealthView enemyHealth;
	private HealthView playerHealth;
	private Label displayPokemon;
    private boolean usingShield;

	public BattleView(Difficulty diff, String backgroundFilePath) {
		setConstants();
		usingShield = false;
		root = new Group();
		scene = new Scene(root, width, height);
		root.getStylesheets().add(CSS_FILE_PATH);
		gameDifficulty = diff;
		setBackground(backgroundFilePath);
		displayPokemon = new Label();
		addButtons(displayX, displayY, "Attack");
		resources = ResourceBundle.getBundle(BATTLE_RESOURCES);
		utilities = new PropertiesUtilities(resources);
	}
	
	private void setConstants() {
		width = utilities.getIntProperty("windowWidth");
		height = utilities.getIntProperty("windowHeight");
		playerX = utilities.getIntProperty("playerX");
		playerY = utilities.getIntProperty("playerY");
		enemyX = utilities.getIntProperty("enemyX");
		enemyY = utilities.getIntProperty("enemyY");
		
	}
	
	public void setModel(BattleModelInView modelInView) {
		this.model = modelInView;
		
		String playerImagePath = utilities.getStringProperty("playerImagePath");
		String enemyImagePath = utilities.getStringProperty("enemyImagePath");
		int messageX = utilities.getIntProperty("messageX");
		int messageY = utilities.getIntProperty("messageY");
		
		enemy = new EnemyView(model.getEnemyHP(), enemyX, enemyY, enemyImagePath);
		player = new PlayerView(model.getPlayerHP(), playerX, playerY, playerImagePath);
		enemyHealth = new HealthView(enemyX, enemyY, (int)model.getEnemyHP());
		playerHealth = new HealthView(playerX, playerY, (int)model.getPlayerHP());
		root.getChildren().addAll(enemyHealth.getGroup(), playerHealth.getGroup());
		enemy.addToGroup(root);
		player.addToGroup(root);
		
		RandomMessage rm = new RandomMessage(root,messageX,messageY);

		displayBattleStats();
		displayNumPokemon();
	}

	private void setBackground(String filePath) {
		Image image = new Image(filePath);
		backgroundView = new ImageView();
		backgroundView.setFitWidth(width);
		backgroundView.setFitHeight(height);
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
		shield = new BattleButton("Shield", x, y + 100);
		shield.addToGroup(root);
		addShieldHandler();
		addReduceHandler();
	}

	private void displayNumPokemon() {
		int displayX = utilities.getIntProperty("displayX");
		int displayY = utilities.getIntProperty("displayY");
		String numPokemonText= utilities.getStringProperty("numPokemonText");
		
		displayPokemon.setText(numPokemonText+ model.getNumPokemon());
		displayPokemon.setLayoutX(displayX);
		displayPokemon.setLayoutY(displayY);
		root.getChildren().add(displayPokemon);
	}
	private void displayBattleStats(){
		String winStatsText= utilities.getStringProperty("winStatsText");
		String lostStatsText= utilities.getStringProperty("lostStatsText");
		Label l = new Label(winStatsText + model.battlesWon() + lostStatsText+ model.battlesLost());
        l.setLayoutX(displayX);
		l.setLayoutY(10);
		l.setFont(new Font("Pokemon GB",10));
		root.getChildren().add(l);
	}

	public void displayTextPokemon() {
		String numPokemonText = utilities.getStringProperty("numPokemonText");
		displayPokemon.setText(numPokemonText + model.getNumPokemon());
		displayPokemon.setFont(new Font("Pokemon GB",10));
	}
	private void addShieldHandler(){
		EventHandler<ActionEvent> event = actionEvent -> {
        	usingShield = true;
		};
		shield.addHandler(event);
	}
	private void addReduceHandler() {
		
		EventHandler<ActionEvent> event = actionEvent -> {
            FireBall f = new FireBall(root);
		    f.throwFireBall(playerX,playerY,enemyX);
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
		String winMessage = utilities.getStringProperty("winMessage");
		model.addBattleWon();
		WinConditionView won = new WinConditionView(winMessage, player);
		won.addToGroup(root);
	}

	private void lose() {
		String loseMessage = utilities.getStringProperty("loseMessage");
		model.reduceNumPokemon();
		if (model.getNumPokemon() <= 0) {
			model.addBattleLost();
			WinConditionView lost = new WinConditionView(loseMessage, enemy);
			lost.addToGroup(root);
		} else {
			model.resetPlayer();
		}
	}
}
