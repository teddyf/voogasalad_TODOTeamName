
package ui.scenes;

import java.util.Stack;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import ui.GridPane;

/**
 * @author Harshil Garg, Nisakorn Valyasevi
 *
 */
public class VoogaAnimation {
	
	private GridPane grid;
	
	private Stack<KeyCode> stack;
	
	private boolean finished;
	private double duration;
	private double pixelMovement;
	private int maxSteps;
	private int stepCount;
	
	private boolean first;
	
	private Timeline animation;

	public VoogaAnimation(GridPane grid) {
		this.grid = grid;
		stack = new Stack<>();
		finished = true;
		first = true;
		duration = 400;
		maxSteps = 800;
		stepCount = 0;
		pixelMovement = grid.getBlockSize()/maxSteps;
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
				animate(code);
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