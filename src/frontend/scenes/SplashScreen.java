package frontend.scenes;

import frontend.uibuilder.ComponentProperties;
import frontend.uibuilder.UIBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SplashScreen extends Scene {

	public SplashScreen(Parent root) {
		super(root, 1000, 300);
		UIBuilder builder = new UIBuilder();
		builder.addNewButton(root, new ComponentProperties(200, 0).message("Hello"));
	}
	

}
