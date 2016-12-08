package ui.scenes.editor;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class DraggableTabPane extends TabPane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mousex = 0;
    private double mousey = 0;
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
            mousex = event.getSceneX();
            mousey = event.getSceneY();
            x = getLayoutX();
            y = getLayoutY();
        });

        //Event Listener for MouseDragged
        onMouseDraggedProperty().set(event -> {

            // Get the exact moved X and Y

            double offsetX = event.getSceneX() - mousex;
            double offsetY = event.getSceneY() - mousey;

            x += offsetX;
            y += offsetY;

            double scaledX = x;
            double scaledY = y;

            setLayoutX(scaledX);
            setLayoutY(scaledY);

            dragging = true;

            // again set current Mouse x AND y position
            mousex = event.getSceneX();
            mousey = event.getSceneY();

            event.consume();
        });

        onMouseClickedProperty().set(event -> dragging = false);

    }
}