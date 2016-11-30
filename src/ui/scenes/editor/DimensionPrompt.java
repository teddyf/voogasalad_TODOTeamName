package ui.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class defines functionality for an alert-style popop that prompts
 *         the user to enter and width and height. The width and height are validated
 *         to ensure that they are correctly formatted integers.
 */
public class DimensionPrompt {

    /**
     * Tests the values in two text fields to ensure that they are valid integers
     * greater than 0 and less than or equal to maxDim
     *
     * @param width  is the first TextField to test
     * @param height is the second TextField to test
     * @param maxDim is the maximum size allowed
     * @return true if the value is not an integer, false otherwise
     */
    private boolean invalidValue(TextField width, TextField height, int maxDim) {
        try {
            int widthVal = Integer.parseInt(width.getText());
            int heightVal = Integer.parseInt(height.getText());
            boolean widthEmpty = width.getText().trim().isEmpty();
            boolean heightEmpty = height.getText().trim().isEmpty();
            return widthEmpty ||
                    heightEmpty ||
                    widthVal > maxDim ||
                    widthVal <= 0 ||
                    heightVal > maxDim ||
                    heightVal <= 0;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    Dimension promptForDimensions(int maxDim) {
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setHeaderText("Please specify a custom width and height less than 100.");

        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField width = new TextField();
        width.setPromptText("width");
        TextField height = new TextField();
        height.setPromptText("height");

        grid.add(new Label("Width:"), 0, 0);
        grid.add(width, 1, 0);
        grid.add(new Label("Height:"), 0, 1);
        grid.add(height, 1, 1);

        Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
        submitButton.setDisable(true);

        width.textProperty().addListener(e -> submitButton.setDisable(invalidValue(width, height, maxDim)));
        height.textProperty().addListener(e -> submitButton.setDisable(invalidValue(width, height, maxDim)));
        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();
        try {
            int widthVal = Integer.parseInt(width.getText());
            int heightVal = Integer.parseInt(height.getText());
            return new Dimension(widthVal, heightVal);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
