package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class ButtonBuilder extends ComponentBuilder {
	
	public ButtonBuilder() {
		super();
	}
	
	@Override
	public Node createComponent(ComponentProperties properties) {
		Button button = new Button();
		button.setLayoutX(properties.x);
		button.setLayoutY(properties.y);
		if (properties.width != 0) {			
			button.setMinWidth(properties.width);
			button.setMaxWidth(properties.width);
		}
		if (properties.height != 0) {
			button.setMinWidth(properties.height);
			button.setMaxWidth(properties.height);
		}
		button.setText(properties.message);
		return button;
	}

}
