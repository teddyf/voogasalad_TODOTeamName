package battle;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleController {
	private BattleView myView;
	private BattleModel myModel;
	
	public BattleController(BattleView view, BattleModel model) {
		myView = view;
		myModel = model;
		myModel.addObserver(myView);
	}
}
