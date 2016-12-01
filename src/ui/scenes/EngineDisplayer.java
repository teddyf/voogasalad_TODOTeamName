package ui.scenes;

import java.util.Observable;
import java.util.Observer;

import engine.EngineController;
import engine.GameInstance;
import resources.properties.PropertiesUtilities;
import ui.GridForEngine;
import ui.GridPane;

public class EngineDisplayer implements Observer {
	
	private EngineController ec;
	private GridForEngine grid;

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
