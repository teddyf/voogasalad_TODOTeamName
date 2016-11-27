package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * @author Robert Steilberg
 *
 * This class builds JavaFX labels.
 */
public class LabelBuilder extends ComponentBuilder {

	public LabelBuilder() {
		super();
	}

	@Override
	public Node createComponent(ComponentProperties properties) {
		Label newLabel = new Label(properties.text);
        newLabel.setId(properties.id);
        newLabel.setLayoutX(properties.x);
        newLabel.setLayoutY(properties.y);
        newLabel.setFont(new Font(properties.font,properties.size));
        return newLabel;
	}
}
