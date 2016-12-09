package battle.view;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import resources.properties.PropertiesUtilities;
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
	public static final int DAMAGE = 10;
	
	private Scene scene;
	private Group root;
	private ImageView backgroundView;
	private EnemyView enemy;
	private PlayerView player;
	private Button reduceHP;
	private HealthView enemyHealth;
	private HealthView playerHealth;
	
	private PropertiesUtilities utilities;
	private static final String BATTLE_RESOURCES = "resources/properties/game-engine-battle";
	private static final String CSS_FILE_NAME = "resources/styles/game-battle.css";
    private ResourceBundle resources;
	
	/*
	 * init battle view using full file path of background image as parameter
	 */
	public BattleView(String backgroundFilePath) {
		root = new Group();
		root.getStylesheets().add(CSS_FILE_NAME);
		resources = ResourceBundle.getBundle(BATTLE_RESOURCES);
		utilities = new PropertiesUtilities();
		initView(backgroundFilePath);
		//TODO delete this
		addButtons(500, 200, "Reduce HP by 10");	
	}
	
	private void initView(String filePath) {
		int windowWidth = utilities.getIntProperty(resources, "windowWidth");
		int windowHeight = utilities.getIntProperty(resources, "windowHeight");
		int playerHealthX = utilities.getIntProperty(resources, "playerHealthX");
		int playerHealthY = utilities.getIntProperty(resources, "playerHealthY");
		int enemyHealthX = utilities.getIntProperty(resources, "enemyHealthX");
		int enemyHealthY = utilities.getIntProperty(resources, "enemyHealthY");
		
		scene = new Scene(root, windowWidth, windowHeight);
		
		Image image = new Image(filePath);
        backgroundView = new ImageView();
        backgroundView.setFitWidth(windowWidth);
        backgroundView.setFitHeight(windowHeight);
        backgroundView.setImage(image);
        backgroundView.setLayoutX(0);
        backgroundView.setLayoutY(0);
        root.getChildren().addAll(backgroundView);
        
        enemyHealth = new HealthView(enemyHealthX,enemyHealthY);
		playerHealth = new HealthView(playerHealthX,playerHealthY);
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
		
		String winMessage = utilities.getStringProperty(resources, "winMessage");
		String loseMessage = utilities.getStringProperty(resources, "loseMessage");
		
		BattleModel model = (BattleModel) o;
		player.setHP(model.getPlayerHP());
		enemy.setHP(model.getEnemyHP());
		
		if (model.checkPlayerLost()) {
            WinConditionView lost = new WinConditionView(loseMessage,enemy);
            lost.addToGroup(root);
		}
		if (model.checkPlayerWon()) {
            WinConditionView won = new WinConditionView(winMessage,player);
            won.addToGroup(root);
		}
		
		enemyHealth.update(enemy);
		playerHealth.update(player);
	}

	public void setModel(BattleModelInView model) {
		int playerX = utilities.getIntProperty(resources, "playerX");
		int playerY = utilities.getIntProperty(resources, "playerY");
		int enemyX = utilities.getIntProperty(resources, "enemyX");
		int enemyY = utilities.getIntProperty(resources, "enemyY");
		
		this.model = model;
		enemy = new EnemyView(model.getEnemyHP(), enemyX, enemyY,"resources/images/battles/pokemon-1.gif");
		player = new PlayerView(model.getPlayerHP(), playerX, playerY,"resources/images/battles/pokemon-2.gif");
		root.getChildren().addAll(enemy.getGroup(),player.getGroup());
		addHandlers();
	}
}
