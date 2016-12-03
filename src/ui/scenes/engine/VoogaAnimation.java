
package ui.scenes.engine;

import java.security.UnresolvedPermission;
import java.util.*;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import engine.EngineController;
import engine.GameInstance;
import player.PlayerUpdate;
import engine.UserInstruction;
import player.PlayerDirection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Nisakorn Valyasevi
 *
 */
public class VoogaAnimation implements Observer {
	private static final String IMAGE_RESOURCE = "resources/images/sprites/Character/Pokemon/";
	private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
	
	private GridForEngine grid;
	
	private Stack<UserInstruction> stack;
	
	private boolean finished;
	private double duration;
	private double pixelMovement;
	private int maxSteps;
	private int stepCount;

	private Character player;
	private Parent root;
	private UIBuilder uiBuilder;
	private ResourceBundle myResources;
	
	private EngineController ec;
	
	private Timeline animation;

	private HashMap<KeyCode, UserInstruction> keyBindings;

	private Group gridLayout;

	public VoogaAnimation(Parent root, GridForEngine grid2, Character player, UIBuilder uiBuilder, EngineController ec) {
		this.root = root;
		this.grid = grid2;
		this.player = player;
		this.uiBuilder = uiBuilder;
		myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
		stack = new Stack<UserInstruction>();
		finished = true;
		duration = 800;
		maxSteps = 800;
		stepCount = 0;
		this.ec= ec;
		pixelMovement = grid2.getBlockSize()/maxSteps;
		keyBindings = new HashMap<KeyCode, UserInstruction>();
		setDefaultKeyBindings();
		gridLayout = grid.getGroup();
	}

	private void setDefaultKeyBindings() {
		keyBindings.put(KeyCode.UP, UserInstruction.UP);
		keyBindings.put(KeyCode.DOWN, UserInstruction.DOWN);
		keyBindings.put(KeyCode.LEFT, UserInstruction.LEFT);
		keyBindings.put(KeyCode.RIGHT, UserInstruction.RIGHT);
	}

	private UserInstruction convertKeyCode(KeyCode code) {
		return keyBindings.get(code);
	}
	
	
	public void handleKeyPress(KeyEvent e) {
		System.out.println("hi1");
		UserInstruction instruction = convertKeyCode(e.getCode());
		if (!stack.contains(instruction))
			stack.push(instruction);
		if (!stack.isEmpty() && finished)
			ec.keyListener(instruction);
	}

	public void handleKeyRelease(KeyEvent e) {
		System.out.println("hi2");
		UserInstruction instruction = convertKeyCode(e.getCode());
		if (stack.contains(instruction))
			stack.remove(instruction);
	}

	private void changePlayerImage(String imageFileName){
		int gridX = Integer.parseInt(myResources.getString("gridX"));
        int gridY = Integer.parseInt(myResources.getString("gridY"));
		System.out.println(imageFileName);
		//uiBuilder.removeComponent(root, player.getCharacterImageView());
		player.setCharacterImage(IMAGE_RESOURCE + imageFileName);
		player.setCharacterImageSize(grid.getBlockSize());
		player.getCharacterImageView().setLayoutX(gridX+grid.getBlockSize()*player.getColumnCharacter());
    	player.getCharacterImageView().setLayoutY(gridY+grid.getBlockSize()*player.getRowCharacter());
		uiBuilder.addComponent(root, player.getCharacterImageView());
	}
	
//	need to clean this up later
	private void changePlayerWalkingDirection(UserInstruction instruction, String playerNumber){
		switch (instruction) {
		case UP:
			changePlayerImage(playerNumber + "NorthWalking.png");
			break;
		case DOWN:
			changePlayerImage(playerNumber + "SouthWalking.png");
			break;
		case RIGHT:
			changePlayerImage(playerNumber + "EastWalking.png");
			break;
		case LEFT:
			changePlayerImage(playerNumber + "WestWalking.png");
			break;
		}
	}
	
	private void changePlayerFacingDirection(UserInstruction instruction, String playerNumber) {
		switch (instruction) {
		case UP:
			changePlayerImage(playerNumber + "NorthFacing.png");
			break;
		case DOWN:
			changePlayerImage(playerNumber + "SouthFacing.png");
			break;
		case RIGHT:
			changePlayerImage(playerNumber + "EastFacing.png");
			break;
		case LEFT:
			changePlayerImage(playerNumber + "WestFacing.png");
			break;
		}
	}

	@Override
	public void update(Observable observable, Object value) {
		System.out.println("hi3");
		if (observable instanceof GameInstance) {
			PlayerUpdate update = (PlayerUpdate) value;
			updatePlayer(update);
		}
	}

	private void updatePlayer(PlayerUpdate update) {
		if (update == PlayerUpdate.ROW || update == PlayerUpdate.COLUMN) {
			processMove(stack.peek());
		}
		if (update == PlayerUpdate.DIRECTION) {
			processDirect(stack.peek());
		}
	}

	private void processMove(UserInstruction key) {
		if (!keyBindings.values().contains(key)) {
			return;
		}
		finished = false;
		animateMove(key);
	}

	private void animateMove(UserInstruction instruction) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(duration/maxSteps),
				e -> move(instruction)));
		animation.play();
	}

	private void move(UserInstruction instruction) {
		if (stepCount == maxSteps) {
			stepCount = 0;
			animation.stop();
			finished = true;
			return;
		}
		double locationX = gridLayout.getLayoutX();
		double locationY = gridLayout.getLayoutY();
		switch (instruction) {
			case UP:
				locationY += pixelMovement;
				break;
			case DOWN:
				locationY -= pixelMovement;
				break;
			case LEFT:
				locationX += pixelMovement;
				break;
			case RIGHT:
				locationX -= pixelMovement;
				break;
		}
		gridLayout.setLayoutX(locationX);
		gridLayout.setLayoutY(locationY);
		stepCount++;
	}

	private void processDirect(UserInstruction key) {
		if (!keyBindings.values().contains(key)) {
			return;
		}
		finished = false;
		animateDirect(key);
	}

	private void animateDirect(UserInstruction instruction) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(duration/maxSteps),
				e -> direct(instruction)));
		animation.play();
	}

	private void direct(UserInstruction key) {

	}
}