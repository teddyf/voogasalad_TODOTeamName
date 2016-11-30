package ui.scenes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.sun.org.apache.xerces.internal.util.Status;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CharacterAnimation {
	
	PathTransition current;
	Stack<KeyCode> stack;
	
	boolean finished;
	
	public CharacterAnimation() {
		stack = new Stack<>();
		current = new PathTransition();
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
	
	private PathTransition buildPathTransition(KeyCode code) {
		switch (code) {
		case RIGHT:
			
		}
	}
}
