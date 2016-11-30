
package ui.scenes;

import java.util.ArrayList;
import java.util.Stack;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	private TranslateTransition current;
	private Stack<KeyCode> stack;
	
	private boolean finished;
	private double duration;
	
	public VoogaAnimation(GridPane grid) {
		this.grid = grid;
		
		stack = new Stack<KeyCode>();
		current = new TranslateTransition();
		finished = false;
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
			current = buildTransition(code);
			current.setOnFinished(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					finished = true;
				}
			});
			current.play();
			finished = false;
		}
	}
	
	private TranslateTransition buildTransition(KeyCode code) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(2000), grid.getGroup());
		switch (code) {
			case UP:
				transition.setByY(grid.getBlockSize());
				break;
			case DOWN:
				transition.setByY(-grid.getBlockSize());
				break;
			case RIGHT:
				transition.setByX(-grid.getBlockSize());
				break;
			case LEFT:
				transition.setByX(grid.getBlockSize());
				break;
			default:
				break;
			
		}
		return transition;
	}
}