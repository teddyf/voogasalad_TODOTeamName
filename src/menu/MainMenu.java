package menu;

import editor.GameEditor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Robert Steilberg
 */
public class MainMenu extends Scene {

    private UIBuilder myBuilder;
    private UILauncher myLauncher;
    private Parent myRoot;

    public MainMenu(UILauncher launcher, Parent root) {
        super(root);
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myRoot = root;
        initMenu();
    }

    private void initMenu() {
        Node button = myBuilder.addNewButton(myRoot, new ComponentProperties(500, 20).message("Editor"));
        button.setOnMouseClicked(e -> myLauncher.launchEditor());
    }
}
