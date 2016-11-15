package menu;

import javafx.scene.Parent;
import javafx.scene.Scene;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Robert Steilberg
 */
public class MainMenu extends Scene {

    public MainMenu(Parent root) {
        super(root);
        UIBuilder builder = new UIBuilder();
        builder.addNewButton(root, new ComponentProperties(500, 0).message("Hello"));
    }
}
