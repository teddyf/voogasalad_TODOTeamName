package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * @author Robert Steilberg
 */
public class DialogBuilder {

    private TextField myTextField;
    private Object myResponse;

    public DialogBuilder(ComponentProperties properties) {
        prompt(properties);
    }

    private void prompt(ComponentProperties properties) {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(properties.header);
        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);
        dialog.setWidth(500);
        myTextField = new TextField();
        Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
        myTextField.textProperty().addListener(e -> submitButton.setDisable(myTextField.getText().trim().isEmpty()));
        submitButton.setDisable(true);

        dialog.setContentText(properties.content);
        dialog.getDialogPane().setContent(myTextField);

        myResponse = dialog.showAndWait().get();
    }

    public String getText() {
        return myTextField.getText();
    }

    public Object getResponse() {
        return myResponse;
    }
}
