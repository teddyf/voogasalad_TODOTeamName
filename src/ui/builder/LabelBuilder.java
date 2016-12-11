package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * @author Robert Steilberg
 *
 * This class builds JavaFX labels.
 */
public class LabelBuilder implements ComponentBuilder {

	public LabelBuilder() {
	}

	public Node createComponent(ComponentProperties properties) {
		Label newLabel = new Label(properties.text);
        newLabel.setId(properties.id);
        newLabel.setLayoutX(properties.x);
        newLabel.setLayoutY(properties.y);
        newLabel.setFont(new Font(properties.font, properties.size));
        if (properties.color != null)
        	newLabel.setTextFill(properties.color);
        return newLabel;
	}
}
