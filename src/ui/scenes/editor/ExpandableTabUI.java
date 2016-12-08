package ui.scenes.editor;

import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Created by Harshil Garg on 12/7/16.
 */
public class ExpandableTabUI {

    private Rectangle myRectangle;
    private Popup myPopup;
    private Stage myStage;

    public ExpandableTabUI(Rectangle rectangle, Popup popup, Stage stage) {
        myRectangle = rectangle;
        myPopup = popup;
        myStage = stage;

        setUpInteraction();
    }

    private void setUpInteraction() {
        myRectangle.setOnMouseClicked(e -> myPopup.show(myStage));
    }
}
