package ui.scenes.editor.sidemenu;

import javafx.scene.control.TabPane;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class implements TabPanes that can be dragged
 *         across the screen with the cursor.
 */
public class DraggableTabPane extends TabPane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mouseX = 0;
    private double mouseY = 0;

    DraggableTabPane() {
        init();
    }

    private void init() {
        onMousePressedProperty().set(event -> {
            // record the current mouse X and Y position on Node
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            x = getLayoutX();
            y = getLayoutY();
        });

        onMouseDraggedProperty().set(event -> {
            // get the exact moved X and Y
            double offsetX = event.getSceneX() - mouseX;
            double offsetY = event.getSceneY() - mouseY;
            x += offsetX;
            y += offsetY;
            double scaledX = x;
            double scaledY = y;
            setLayoutX(scaledX);
            setLayoutY(scaledY);
            // again set current Mouse x and y position
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            event.consume();
        });
    }
}