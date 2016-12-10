package ui.scenes.editor.sidemenu;

import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class implements draggable TabPanes.
 */
public class DraggableTabPane extends TabPane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mouseX = 0;
    private double mouseY = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;

    public DraggableTabPane() {
        init();
    }

    public DraggableTabPane(Node view) {
        this.view = view;
        getChildren().add(view);
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
            dragging = true;
            // again set current Mouse x and y position
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            event.consume();
        });
        onMouseClickedProperty().set(event -> dragging = false);
    }
}