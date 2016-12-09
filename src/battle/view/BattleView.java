package battle.view;

import java.util.Observable;
import java.util.Observer;

import battle.model.BattleModel;
import ui.media.SoundControl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleView implements Observer {
	BattleModelInView model;
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 500;
	protected static final int OFFSET = 40;
    protected static final int OFFSET_Y = 20;
	protected static final int RECTANGLE_WIDTH = 40;
	protected static final int RECTANGLE_HEIGHT = 40;
	protected static final Color BACKGROUND = Color.AZURE;
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
	private Button reduceHP;
	private HealthView enemyHealth;
	private HealthView playerHealth;
	
	private static final String CSS_FILE_NAME = "resources/styles/game-engine.css";

	/*
	 * init battle view using full file path of background image as parameter
	 */
	public BattleView(String backgroundFilePath) {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT);
		root.getStylesheets().add(CSS_FILE_NAME);
		setView(backgroundFilePath);
		addButtons(500, 200, "Reduce HP by 10");	
	}
	
	private void setView(String filePath) {
		Image image = new Image(filePath);
        backgroundView = new ImageView();
        backgroundView.setFitWidth(WIDTH);
        backgroundView.setFitHeight(HEIGHT);
        backgroundView.setImage(image);
        backgroundView.setLayoutX(0);
        backgroundView.setLayoutY(0);
        root.getChildren().addAll(backgroundView);
        
        enemyHealth = new HealthView(ENEMY_X+100,ENEMY_Y+200);
		playerHealth = new HealthView(PLAYER_X-100,PLAYER_Y+200);
		root.getChildren().addAll(enemyHealth.getGroup(),playerHealth.getGroup());
		
		SoundControl soundControl = new SoundControl();
		root.getChildren().add(soundControl.getGroup());
		
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
            if(!(model.checkPlayerLost() || model.checkPlayerWon())) {
            	model.setEnemyHP(model.getEnemyHP() - DAMAGE);
            }
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		BattleModel model = (BattleModel) o;
		player.setHP(model.getPlayerHP());
		enemy.setHP(model.getEnemyHP());
		
		if (model.checkPlayerLost()) {
            WinConditionView lost = new WinConditionView("You lost");
            lost.addToGroup(root);
		}
		if (model.checkPlayerWon()) {
            WinConditionView won = new WinConditionView("You won");
            won.addToGroup(root);
		}
		
		enemyHealth.update(enemy);
		playerHealth.update(player);
	}

	public void setModel(BattleModelInView model) {
		this.model = model;
		enemy = new EnemyView(model.getEnemyHP(), ENEMY_X+100, ENEMY_Y,"resources/images/battles/pokemon-1.gif");
		player = new PlayerView(model.getPlayerHP(), PLAYER_X-100, PLAYER_Y,"resources/images/battles/pokemon-2.gif");
		root.getChildren().addAll(enemy.getGroup(),player.getGroup());
		addHandlers();
	}
}
