package utilities.builder;

import javafx.scene.control.*;

import java.util.Optional;

/**
 * @author Robert Steilberg
 *         <p>
 *         Creates a simple dialog popup that gets a text response.
 */
public class DialogBuilder {

    private Optional<String> myResponse;

    public DialogBuilder(ComponentProperties properties) {
        prompt(properties);
    }

    /**
     * Prompts the user to enter a text input
     *
     * @param properties are the ComponentProperties containing the header
     *                   and content to be displayed in the dialog
     */
    private void prompt(ComponentProperties properties) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(properties.header);
        dialog.setContentText(properties.content);
        myResponse = dialog.showAndWait();
    }

    /**
     * Gets the user's response from the text input
     *
     * @return the user's response
     */
    public Optional<String> getResponse() {
        return myResponse;
    }
}
