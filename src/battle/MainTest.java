package battle;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BattleView view = new BattleView();
		primaryStage.setScene(view.getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
