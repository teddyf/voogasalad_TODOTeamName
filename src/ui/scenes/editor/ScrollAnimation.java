package ui.scenes.editor;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * @author Harshil Garg
 *         <p>
 *         Adds scrolling to the grid for navigation.
 */
public class ScrollAnimation {

    private TranslateTransition transition;

    private double CURRENT_MODE_SCROLL_SPEED_PPM;

    private double BUTTON_MODE_SCROLL_SPEED_PPM = 0.2;
    private double TRACKPAD_MODE_SCROLL_SPEED_PPM = 0.5;

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
        if (duration > 0) {
            transition = new TranslateTransition(Duration.millis(duration), group);
            transition.setCycleCount(1);
            transition.setInterpolator(Interpolator.LINEAR);
        }
    }

    public void setScrollSpeedButtons() {
        CURRENT_MODE_SCROLL_SPEED_PPM = BUTTON_MODE_SCROLL_SPEED_PPM;
    }

    public void setScrollSpeedTrackpad() {
        CURRENT_MODE_SCROLL_SPEED_PPM = TRACKPAD_MODE_SCROLL_SPEED_PPM;
    }

    public void left() {
        double duration = (xMax - group.getLayoutX()) / CURRENT_MODE_SCROLL_SPEED_PPM;
        //double duration = 1000;
        setTransition(duration);
        transition.setToX(xMax);
    }

    public void right() {
        double duration = (group.getLayoutX() - xMin) / CURRENT_MODE_SCROLL_SPEED_PPM;
        //double duration = 1000;
        setTransition(duration);
        transition.setToX(xMin);
    }

    public void up() {
        double duration = (yMax - group.getLayoutY()) / CURRENT_MODE_SCROLL_SPEED_PPM;
        //double duration = 1000;
        setTransition(duration);
        transition.setToY(yMax);
    }

    public void down() {
        double duration = (group.getLayoutY() - yMin) / CURRENT_MODE_SCROLL_SPEED_PPM;
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
