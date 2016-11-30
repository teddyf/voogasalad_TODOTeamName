
package ui.scenes;

import java.util.Stack;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import ui.GridPane;

/**
 * @author Harshil Garg, Nisakorn Valyasevi
 *
 */
public class VoogaAnimation {
	
	Group group;
	
	TranslateTransition current;
	Stack<KeyCode> stack;
	
	boolean finished;
	
	public VoogaAnimation(Group element) {
		group = element;
		group.setLayoutY(200);
		stack = new Stack<>();
		current = new TranslateTransition();
		finished = true;
	}
	
	public void handleKeyPress(KeyEvent e) {
		System.out.println(e.getCode());
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
			System.out.println("uhfgf");
			System.out.println(stack.peek());
			current = buildPathTransition(stack.peek());
			current.setOnFinished(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					finished = true;
					process();
				}
			});
			current.play();
			finished = false;
		}
	}
	
	private TranslateTransition buildPathTransition(KeyCode code) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(2000), group);
		transition.setByY(200);
		return transition;
	}
}