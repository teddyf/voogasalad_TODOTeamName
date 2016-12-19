package utilities.builder;

import javafx.scene.Node;

/**
 * @author Harshil Garg, Nisakorn Valyasevi
 *         <p>
 *         Interface used to add JavaFX nodes to a scene.
 */
public interface ComponentBuilder {

    Node createComponent(ComponentProperties properties);
}