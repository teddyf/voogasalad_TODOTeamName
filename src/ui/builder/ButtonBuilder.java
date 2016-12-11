package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds JavaFX buttons.
 */
public class ButtonBuilder implements ComponentBuilder {

    public ButtonBuilder() {
    }

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
        button.setText(properties.text);
        return button;
    }
}
