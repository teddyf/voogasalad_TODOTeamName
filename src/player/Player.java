package player;

import java.util.ArrayList;
import java.util.List;

import editor.backend.Battle;
import editor.backend.Block;
import editor.backend.Interaction;
import editor.backend.Item;
import editor.backend.Status;

public class Player implements IPlayer {
	private String myName;
	private Block myBlock;
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
	
	public Block getBlock() {
		return myBlock;
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
	
	public void setBlock(Block block) {
		myBlock = block;
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