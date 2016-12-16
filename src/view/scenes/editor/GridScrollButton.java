package view.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;
import utilities.PropertiesUtilities;
import utilities.builder.ComponentProperties;
import utilities.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg
 */
public class GridScrollButton {

    private static final String SCROLL_RESOURCES = "resources/properties/scroll";

    private Parent myRoot;
    private ScrollAnimation myScrollAnimation;

    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private PropertiesUtilities myUtil;

    private ImageView upScroll;
    private ImageView rightScroll;
    private ImageView downScroll;
    private ImageView leftScroll;

    private Circle center;

    GridScrollButton(Parent root, ScrollAnimation scrollAnimation) {
        myRoot = root;
        myScrollAnimation = scrollAnimation;

        myResources = ResourceBundle.getBundle(SCROLL_RESOURCES);
        myBuilder = new UIBuilder();
        myUtil = new PropertiesUtilities(myResources);

        setUpButtons();
        setUpListeners();
    }

    private void setUpButtons() {
        String path = myUtil.getStringProperty("image");

        Pane controls = (Pane) myBuilder.addComponent(myRoot, new Pane());
        controls.setLayoutX(0);
        controls.setLayoutY(530);
        controls.setPadding(new Insets(5, 5, 5, 5));
        controls.setId("pen");

        ComponentProperties up = new ComponentProperties(50, 10)
                .path(path)
                .width(50)
                .height(50)
                .rotate(270);

        ComponentProperties right = new ComponentProperties(90, 50)
                .path(path)
                .width(50)
                .height(50)
                .rotate(0);

        ComponentProperties down = new ComponentProperties(50, 90)
                .path(path)
                .width(50)
                .height(50)
                .rotate(90);

        ComponentProperties left = new ComponentProperties(10, 50)
                .path(path)
                .width(50)
                .height(50)
                .rotate(180);

        upScroll = (ImageView) myBuilder.addNewImageView(controls, up);
        rightScroll = (ImageView) myBuilder.addNewImageView(controls, right);
        downScroll = (ImageView) myBuilder.addNewImageView(controls, down);
        leftScroll = (ImageView) myBuilder.addNewImageView(controls, left);

        center = (Circle) myBuilder.addComponent(controls, new Circle(75, 75, 12, Color.AZURE));

    }

    private void setUpListeners() {
        upScroll.setOnMouseEntered(e -> {
            myScrollAnimation.setScrollSpeedButtons();
            myScrollAnimation.up();
            myScrollAnimation.play();
        });
        upScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        rightScroll.setOnMouseEntered(e -> {
            myScrollAnimation.setScrollSpeedButtons();
            myScrollAnimation.right();
            myScrollAnimation.play();
        });
        rightScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        downScroll.setOnMouseEntered(e -> {
            myScrollAnimation.setScrollSpeedButtons();
            myScrollAnimation.down();
            myScrollAnimation.play();
        });
        downScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        leftScroll.setOnMouseEntered(e -> {
            myScrollAnimation.setScrollSpeedButtons();
            myScrollAnimation.left();
            myScrollAnimation.play();
        });
        leftScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        center.setOnMouseClicked(e -> {
            myScrollAnimation.setScrollSpeedButtons();
            myScrollAnimation.center();
            myScrollAnimation.play();
        });
    }

    public void trackpadStartScroll(ScrollEvent event) {
        // Horizontal movement
        myScrollAnimation.setScrollSpeedTrackpad();
        double x = event.getDeltaX();
        double y = event.getDeltaY();
        if (Math.abs(x) > Math.abs(y)) {
            if (event.getDeltaX() > 0) {
                myScrollAnimation.left();
            } else {
                myScrollAnimation.right();
            }
        }
        // Vertical movement
        else {
            if (event.getDeltaY() > 0) {
                myScrollAnimation.up();
            } else {
                myScrollAnimation.down();
            }
        }
        myScrollAnimation.play();
    }

    public void trackpadEndScroll(ScrollEvent event) {
        myScrollAnimation.stop();
    }

}