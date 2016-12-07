package ui.scenes.editor;

import javafx.scene.image.ImageView;
import javafx.scene.Parent;

/**
 * Created by harshilgarg on 12/7/16.
 */
public class GridScrollButton {

    public ImageView leftButton;
    public ImageView rightButton;
    public ImageView topButton;
    public ImageView bottomButton;

    private Parent myRoot;

    public GridScrollButton(Parent root) {
        myRoot = root;
        leftButton = new ImageView();
        rightButton = new ImageView();
        topButton = new ImageView();
        bottomButton = new ImageView();
    }

    public void setUpButtons() {
        "resources/images/tiles/decorations/dirt-1";
    }

}
