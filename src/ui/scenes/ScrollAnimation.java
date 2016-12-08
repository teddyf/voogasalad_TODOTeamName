package ui.scenes;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * Created by Harshil Garg on 12/7/2016
 */
public class ScrollAnimation {

    private TranslateTransition transition;

    private double pixelsPerMillisecond = 0.1;

    private double xMax;
    private double xMin;
    private double yMax;
    private double yMin;

    private Group group;

    public ScrollAnimation(Group group, double xMin, double yMin) {
        this.group = group;
        this.xMin = xMin;
        this.xMax = -xMin;
        this.yMin = yMin;
        this.yMax = -yMin;
    }

    private void setTransition(double duration) {
        transition = new TranslateTransition(Duration.millis(duration), group);
        transition.setCycleCount(1);
        transition.setInterpolator(Interpolator.LINEAR);
    }

    public void left() {
        double duration = (xMax - group.getLayoutX())/pixelsPerMillisecond;
        //double duration = 1000;
        setTransition(duration);
        transition.setToX(xMax);
    }

    public void right() {
        double duration = (group.getLayoutX() - xMin)/pixelsPerMillisecond;
        //double duration = 1000;
        setTransition(duration);
        transition.setToX(xMin);
    }

    public void up() {
        double duration = (yMax - group.getLayoutY())/pixelsPerMillisecond;
        //double duration = 1000;
        setTransition(duration);
        transition.setToY(yMax);
    }

    public void down() {
        double duration = (group.getLayoutY() - yMin)/pixelsPerMillisecond;;
        //double duration = 1000;
        setTransition(duration);
        transition.setToY(yMin);
    }

    public void center() {
        double duration = 1000;
        setTransition(duration);
        transition.setToX(0);
        transition.setToY(0);
    }

    public void play() {
        transition.play();
    }

    public void stop() {
        transition.stop();
    }

}
