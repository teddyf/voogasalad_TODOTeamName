package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public class TextFieldBuilder extends ComponentBuilder {
    @Override
    public Node createComponent(ComponentProperties properties) {
        TextField textField = new TextField();
        textField.setId(properties.id);
        textField.setLayoutX(properties.x);
        textField.setLayoutY(properties.y);
        textField.setPromptText(properties.text);
        textField.setMinWidth(properties.width);
        textField.setMinHeight(properties.height);
        return textField;
    }
}