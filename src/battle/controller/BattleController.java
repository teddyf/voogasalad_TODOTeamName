package battle.controller;

import battle.model.BattleModel;
import battle.view.BattleView;

/**
 * @author Daniel Chai
 */
public class BattleController {
	private BattleView myView;
	private BattleModel myModel;
	
	public BattleController(BattleView view, BattleModel model) {
		myView = view;
		myModel = model;
		myView.setModel(myModel);
		myView.displayNumPokemon();
		myModel.addObserver(myView);
	}
	
	public BattleView getView() {
		return myView;
	}
}
