package ui.scenes;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
/**
 * Created by Harshil Garg on 12/7/2016
 */
public class ScrollAnimation {

    private TranslateTransition left;
    //private TranslateTransition up;
    private TranslateTransition right;
    //private TranslateTransition down;

    private double pixelsPerMillisecond = 0.1;

    int xMax = 200;
    int xMin = -200;

    private Group group;

    public ScrollAnimation(Group group) {
        this.group = group;
        left = new TranslateTransition();
        //up = new TranslateTransition();
        right = new TranslateTransition();
        //determineValues();

    }

    public void traverseLeft() {
        // Start position the layout is definitely (0, 0) of the grid
        // For going left, we do setLayoutX(-someMaxValue)
        // For going left, we do setLayoutX(someMaxValue)
        // For going up, we do setLayoutY(someMaxValue)
        // For going down, we do setLayoutY(-someMaxValue)

        // Going left, means moving the grid to the right
            //Get current layout x, and at start
        double duration = (xMax - group.getLayoutX())/pixelsPerMillisecond;
        left = new TranslateTransition(Duration.millis(duration), group);
        left.setCycleCount(1);
        left.setInterpolator(Interpolator.LINEAR);
        left.setToX(xMax);
        left.setNode(group);
    }

    public void playLeft() {
        left.play();
    }

    public void stopLeft() {
        left.stop();
        //left.setNode(null);
    }

    public void traverseRight() {
        double duration = (group.getLayoutX() - xMin)/pixelsPerMillisecond;
        right = new TranslateTransition(Duration.millis(duration), group);
        right.setCycleCount(1);
        right.setInterpolator(Interpolator.LINEAR);
        right.setToX(xMin);
        right.setNode(group);
    }

    public void playRight() {
        right.play();
    }

    public void stopRight() {
        right.stop();
    }



}
