package battle;
import battle.controller.BattleController;
import battle.model.BattleModel;
import block.EnemyBlock;
import javafx.application.Application;
import javafx.stage.Stage;
import player.Player;
import battle.view.*;
public class MainTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Player player = new Player("Player", 0, 0, 0);
		EnemyBlock enemy = new EnemyBlock("Enemy", 0, 0);

		BattleView view;
		view = new BattleView(BattleView.Difficulty.MEDIUM, "resources/images/battles/background/background-1.jpg");
		BattleModel model = new BattleModel(player, enemy);
		BattleController controller = new BattleController(view, model);
		
		primaryStage.setScene(controller.getView().getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
