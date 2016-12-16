package utilities.builder;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds JavaFX radio buttons.
 */
public class RadioButtonBuilder implements ComponentBuilder {

    RadioButtonBuilder() {
    }

    public Node createComponent(ComponentProperties properties) {
        RadioButton newButton = new RadioButton(properties.text);
        newButton.setId(properties.id);
        newButton.setLayoutX(properties.x);
        newButton.setLayoutY(properties.y);
        newButton.setToggleGroup(properties.toggleGroup);
        newButton.setSelected(properties.selected);
        return newButton;
    }
}
