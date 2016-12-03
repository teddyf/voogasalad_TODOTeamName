
package ui.scenes.engine;

import java.util.ResourceBundle;
import java.util.Stack;

import player.PlayerDirection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import ui.GridPane;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Nisakorn Valyasevi
 *
 */
public class VoogaAnimation {
	private static final String IMAGE_RESOURCE = "resources/images/sprites/Character/Pokemon/";
	private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
	
	private GridForEngine grid;
	
	private Stack<KeyCode> stack;
	
	private boolean finished;
	private double duration;
	private double pixelMovement;
	private int maxSteps;
	private int stepCount;
	private Character player;
	private Parent root;
	private UIBuilder uiBuilder;
	private ResourceBundle myResources;
	
	private boolean first;
	
	private Timeline animation;

	public VoogaAnimation(Parent root, GridForEngine grid2, Character player, UIBuilder uiBuilder) {
		this.root = root;
		this.grid = grid2;
		this.player = player;
		this.uiBuilder = uiBuilder;
		myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
		stack = new Stack<>();
		finished = true;
		first = true;
		duration = 800;
		maxSteps = 800;
		stepCount = 0;
		pixelMovement = grid2.getBlockSize()/maxSteps;
	}
	
	
	public void handleKeyPress(KeyEvent e) {
		KeyCode code = e.getCode();
		if (!stack.contains(code))
			stack.push(code);
		process();
			
	}
	
	public void handleKeyRelease(KeyEvent e) {
		KeyCode	code = e.getCode();
		if (stack.contains(code)) {
			stack.remove(code);
		}
	}
	
	public void process() {
		if (!stack.isEmpty() && finished) {
			KeyCode code = stack.peek();
				changePlayerWalkingDirection(code, "Player1"); //TODO:no hardcode playernumber
				animate(code);
				changePlayerFacingDirection(code, "Player1");
			finished = false;
		}
	}
	
	private void animate(KeyCode code) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(new KeyFrame(Duration.millis(duration/maxSteps),
				e -> move(code)));
		animation.play();
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
	private void changePlayerWalkingDirection(KeyCode code, String playerNumber){
		switch (code) {
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
	
	private void changePlayerFacingDirection(KeyCode code, String playerNumber) {
		switch (code) {
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
	
	private void move(KeyCode code) {
		Group group = grid.getGroup();
		
		if (stepCount == maxSteps) {
			stepCount = 0;
			animation.stop();
			finished = true;
			return;
		}
		switch (code) {
			case UP:
				group.setLayoutY(group.getLayoutY() + pixelMovement);
				break;
			case DOWN:
				group.setLayoutY(group.getLayoutY() - pixelMovement);
				break;
			case RIGHT:
				group.setLayoutX(group.getLayoutX() - pixelMovement);
				break;
			case LEFT:
				group.setLayoutX(group.getLayoutX() + pixelMovement);
				break;
			default:
				break;
		}
		stepCount++;
	}
}