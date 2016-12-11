package ui.builder;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class builds JavaFX ImageViews.
 */
public class ImageViewBuilder implements ComponentBuilder {

    ImageViewBuilder() {
        super();
    }

    public Node createComponent(ComponentProperties properties) {
        Image rawImage = new Image(properties.path);
        ImageView imageView = new ImageView(rawImage);
        imageView.setId(properties.id);
        imageView.setLayoutX(properties.x);
        imageView.setLayoutY(properties.y);
        imageView.setPreserveRatio(properties.preserveRatio);
        imageView.setFitWidth(properties.width);
        imageView.setFitHeight(properties.height);
        imageView.setRotate(properties.rotate);
        return imageView;
    }
}