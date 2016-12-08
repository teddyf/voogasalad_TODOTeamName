package battle;

import block.EnemyBlock;
import javafx.application.Application;
import javafx.stage.Stage;
import player.Player;

public class MainTest extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Player player = new Player("Player", 0, 0, 0);
		EnemyBlock enemy = new EnemyBlock("Enemy", 0, 0);
		
		BattleView view = new BattleView();
		BattleModel model = new BattleModel(player, enemy);
		BattleController controller = new BattleController(view, model);
		
		primaryStage.setScene(controller.getView().getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
