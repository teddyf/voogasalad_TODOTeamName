package view.scenes.editor;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import utilities.builder.ComponentProperties;
import utilities.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class defines functionality for an alert-style popop that prompts
 *         the user to enter and width and height. The width and height are validated
 *         to ensure that they are correctly formatted integers.
 */
class DimensionPrompt {

    private Parent myRoot;
    private static ResourceBundle myResources;

    DimensionPrompt(Parent root, ResourceBundle resources) {
        myRoot = root;
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

    /**
     * Creates a model.grid used to position two TextFields
     *
     * @param hGap        is the horizontal gap between each model.grid cell
     * @param vGap        is the vertical gap between each model.grid cell
     * @param topField    is the top-most TextField of the model.grid
     * @param bottomField is the bottom-most TextField of the model.grid
     * @return the model.grid with the TextFields added
     */
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

    /**
     * Creates a dialog with two fields; the dialog can be submitted only when the two fields contain
     * legal integers according to maxDim
     *
     * @param maxDim      is the largest allowed dimension
     * @param topField    is the top-most field of the dialog
     * @param bottomField is the bottom-most field of the dialog
     * @return the dialog
     */
    private Dialog<Pair<Integer, Integer>> twoFieldDialog(int maxDim, TextField topField, TextField bottomField) {
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

    /**
     * Creates a dialog and prompts the user to enter a width and height to be used as dimensions;
     * the user-specified width and height are validated to ensure that they are legal integers.
     *
     * @param maxDim is the largest allowed dimension
     * @return a Dimension object holding the width and height given by the user
     */
    Dimension promptForDimensions(int maxDim) {
        UIBuilder builder = new UIBuilder();
        TextField widthField = (TextField) builder.addNewTextField(myRoot, new ComponentProperties()
                .text(myResources.getString("topFieldPromptText"))
                .width(200));
        TextField heightField = (TextField) builder.addNewTextField(myRoot, new ComponentProperties()
                .text(myResources.getString("bottomFieldPromptText"))
                .width(200));
        Dialog dimDialog = twoFieldDialog(maxDim, widthField, heightField);
        dimDialog.getDialogPane().setContent(createGrid(10, 10, widthField, heightField));
        if (dimDialog.showAndWait().get() == ButtonType.CANCEL) return null; // user clicked cancel
        int widthVal = Integer.parseInt(widthField.getText());
        int heightVal = Integer.parseInt(heightField.getText());
        return new Dimension(widthVal, heightVal);
    }
}
