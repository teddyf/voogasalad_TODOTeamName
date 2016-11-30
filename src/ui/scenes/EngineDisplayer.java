package ui.scenes;

import java.util.Observable;
import java.util.Observer;

import engine.EngineController;
import engine.GameInstance;

public class EngineDisplayer implements Observer {
	
	private EngineController ec;

	public EngineDisplayer(EngineController ec) {
		this.ec = ec;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GameInstance) {
			System.out.println("hi");
		}
	}

}
