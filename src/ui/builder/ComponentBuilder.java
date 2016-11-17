package ui.builder;

import javafx.scene.Node;

/**
 * @author Harshil Garg
 *         <p>
 *         Abstract class used to add JavaFX nodes to a scene.
 */
public abstract class ComponentBuilder {

    public ComponentBuilder() {
    }

    public abstract Node createComponent(ComponentProperties properties);
}