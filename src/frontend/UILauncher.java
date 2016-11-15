package frontend;

import frontend.scenes.SplashScreen;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * @author Harshil Garg
 */
public class UILauncher {
	
	private Stage stage;
	private SplashScreen splash;
	
	public UILauncher(Stage stage) {
		this.stage = stage;
		initializeStage();
		initializeScreens();
		navigateToSplashScreen();
	}
	
	private void initializeStage() {
		stage.setTitle("VOOGASalad Game Engine");
		stage.setResizable(false);
		stage.setFullScreen(true);
		stage.show();
	}
	
	private void initializeScreens() {
		splash = new SplashScreen(new Group());
	}
	
	public void navigateToSplashScreen() {
		stage.setScene(splash);
	}
	
}
