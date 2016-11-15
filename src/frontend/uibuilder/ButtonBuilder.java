package frontend.uibuilder;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ButtonBuilder extends ComponentBuilder {
	
	public ButtonBuilder(Parent layout) {
		super(layout);
	}
	
	@Override
	public Node createComponent() {
		return null;
	}
	
	@Override
	public Node createComponent(ComponentProperties properties) {
		Button button = new Button();
		button.setLayoutX(properties.x);
		button.setLayoutY(properties.y);
		button.setText(properties.message);
		return addComponent(button);
	}

}
