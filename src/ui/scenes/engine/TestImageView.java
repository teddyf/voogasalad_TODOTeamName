package ui.scenes.engine;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by anindamanocha on 12/11/16.
 */
public class TestImageView {

    public static void main(String[] args) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("resources/images/sprites/1-down.png"));
        Button newButton = new Button();
        newButton.setOnMouseClicked(e -> {
            imageView.setImage(new Image("resources/images/sprites/1-left.png"));
        });
    }
}
