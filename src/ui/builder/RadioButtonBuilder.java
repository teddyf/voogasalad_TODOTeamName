package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds JavaFX radio buttons.
 */
public class RadioButtonBuilder extends ComponentBuilder {

    public RadioButtonBuilder() {
        super();
    }

    @Override
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
