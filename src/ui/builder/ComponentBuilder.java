package ui.builder;

import javafx.scene.Node;

public abstract class ComponentBuilder {

    public ComponentBuilder() {
    }

    public abstract Node createComponent(ComponentProperties properties);

}