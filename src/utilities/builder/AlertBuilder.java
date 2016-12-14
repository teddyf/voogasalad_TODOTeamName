package utilities.builder;

import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds simple JavaFX alerts.
 */
public class AlertBuilder implements ComponentBuilder {

    public AlertBuilder() {
    }

    public Node createComponent(ComponentProperties properties) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(properties.title);
        alert.setHeaderText(properties.header);
        alert.setContentText(properties.content);
        alert.showAndWait();
        return null;
    }
}
