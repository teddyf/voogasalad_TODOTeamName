package ui.scenes.editor;

import javafx.scene.Parent;
import resources.properties.PropertiesUtilities;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import java.util.ResourceBundle;

/**
 * Created by harshilgarg on 12/7/16.
 */
public class GridScrollButton {

    private static final String SCROLL_RESOURCES = "resources/properties/scroll";

    private Parent myRoot;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private PropertiesUtilities myUtil;

    public GridScrollButton(Parent root) {
        myRoot = root;
        myResources = ResourceBundle.getBundle(SCROLL_RESOURCES);
        myBuilder = new UIBuilder();
        myUtil = new PropertiesUtilities(myResources);

        setUpButtons();
    }

    private void setUpButtons() {
        String path = myUtil.getStringProperty("image");

        ComponentProperties up = new ComponentProperties(200, 200)
                .path(path)
                .width(50)
                .height(50)
                .rotate(0);

        ComponentProperties right = new ComponentProperties(250, 250)
                .path(path)
                .width(50)
                .height(50)
                .rotate(90);

        ComponentProperties down = new ComponentProperties(200, 300)
                .path(path)
                .width(50)
                .height(50)
                .rotate(180);

        ComponentProperties left = new ComponentProperties(150, 250)
                .path(path)
                .width(50)
                .height(50)
                .rotate(270);

        myBuilder.addNewImageView(myRoot, up);
        myBuilder.addNewImageView(myRoot, right);
        myBuilder.addNewImageView(myRoot, down);
        myBuilder.addNewImageView(myRoot, left);

    }

}
