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
        button.setId(properties.id);
		button.setLayoutX(properties.x);
		button.setLayoutY(properties.y);
		if (properties.width != 0) {			
			button.setMinWidth(properties.width);
			button.setMaxWidth(properties.width);
		}
		if (properties.height != 0) {
			button.setMinHeight(properties.height);
			button.setMaxHeight(properties.height);
		}
		button.setText(properties.message);
		return button;
	}

}
