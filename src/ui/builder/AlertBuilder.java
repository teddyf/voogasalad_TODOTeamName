package ui.builder;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds simple JavaFX alerts.
 */
public class AlertBuilder extends ComponentBuilder {

    public AlertBuilder() {
        super();
    }

    @Override
    public Node createComponent(ComponentProperties properties) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(properties.title);
        alert.setHeaderText(properties.header);
        alert.setContentText(properties.content);
        alert.showAndWait();
        return null;
    }
}
