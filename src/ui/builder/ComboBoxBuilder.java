package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;

/**
 * @author Robert Steilberg
 */
public class ComboBoxBuilder<E> implements ComponentBuilder {

    ComboBoxBuilder() {
    }

    public Node createComponent(ComponentProperties properties) {
        ComboBox<E> newComboBox = new ComboBox<>();
        newComboBox.setLayoutX(properties.x);
        newComboBox.setLayoutY(properties.y);
        newComboBox.setId(properties.id);
        newComboBox.setItems(properties.options);
        return newComboBox;
    }

}
