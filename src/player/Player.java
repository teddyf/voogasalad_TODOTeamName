package player;

import java.util.ArrayList;
import java.util.List;

import editor.backend.Battle;
import editor.backend.Interaction;
import editor.backend.Item;
import editor.backend.Status;

public class Player implements IPlayer {
	
	private String myName;
	private int myRow;
	private int myCol;
	private List<Item> myInventory;
	private List<Battle> myBattleHistory;
	private List<Interaction> myInteractionHistory;
	private List<Status> myStatus;
	
	public Player(String name) {
		myName = name;
		myInventory = new ArrayList<Item>();
		myBattleHistory = new ArrayList<Battle>();
		myInteractionHistory = new ArrayList<Interaction>();
		myStatus = new ArrayList<Status>();
	}

	public String getPlayerName() {
		return myName;
	}
	
	public int getRow() {
		return myRow;
	}
	
	public int getCol() {
		return myCol;
	}
	
	public List<Item> getInventory() {
		return myInventory;
	}

	public List<Battle> getBattleHistory() {
		return myBattleHistory;
	}

	public List<Interaction> getInteractionHistory() {
		return myInteractionHistory;
	}

	public List<Status> getPlayerStatus() {
		return myStatus;
	}
	
	public void setRow(int row) {
		myRow = row;
	}
	
	public void setCol(int col) {
		myCol = col;
	}
	
	public void addItem(Item item) {
		myInventory.add(item);
	}
	
	public void addBattle(Battle battle) {
		myBattleHistory.add(battle);
	}
	
	public void addInteraction(Interaction interaction) {
		myInteractionHistory.add(interaction);
	}
	
	public void addStatus(Status status) {
		myStatus.add(status);
	}
}