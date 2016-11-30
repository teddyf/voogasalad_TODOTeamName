package ui.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class defines functionality for an alert-style popop that prompts
 *         the user to enter and width and height. The width and height are validated
 *         to ensure that they are correctly formatted integers.
 */
public class DimensionPrompt {

    private static ResourceBundle myResources;

    DimensionPrompt(ResourceBundle resources) {
        myResources = resources;
    }

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

    private GridPane createGrid(int hGap, int vGap, TextField topField, TextField bottomField) {
        GridPane grid = new GridPane();
        grid.setHgap(hGap);
        grid.setVgap(vGap);
        grid.add(new Label(myResources.getString("topFieldLabel")), 0, 0);
        grid.add(topField, 1, 0);
        grid.add(new Label(myResources.getString("bottomFieldLabel")), 0, 1);
        grid.add(bottomField, 1, 1);
        return grid;
    }

    private Dialog<Pair<Integer, Integer>> twoFieldDialog(int maxDim,TextField topField, TextField bottomField) {
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setHeaderText(myResources.getString("dimHeader"));
        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);
        Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
        topField.textProperty().addListener(e -> submitButton.setDisable(invalidValue(topField, bottomField, maxDim)));
        bottomField.textProperty().addListener(e -> submitButton.setDisable(invalidValue(topField, bottomField, maxDim)));
        submitButton.setDisable(true);
        return dialog;
    }

    Dimension promptForDimensions(int maxDim) {
        TextField widthField = new TextField();
        widthField.setPromptText(myResources.getString("topFieldPromptText"));
        TextField heightField = new TextField();
        heightField.setPromptText(myResources.getString("bottomFieldPromptText"));

        Dialog dimDialog = twoFieldDialog(maxDim,widthField,heightField);

        GridPane dialogGrid = createGrid(10,10,widthField,heightField);


        dimDialog.getDialogPane().setContent(dialogGrid);

        dimDialog.showAndWait();
        try {
            int widthVal = Integer.parseInt(widthField.getText());
            int heightVal = Integer.parseInt(heightField.getText());
            return new Dimension(widthVal, heightVal);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
