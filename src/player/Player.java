package player;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import api.IPlayer;
import editor.backend.Battle;
import interactions.Interaction;
import editor.backend.Item;
import editor.backend.Status;

/**
 * This is the class that holds all of the information corresponding to the player
 * @author Aninda Manocha
 */

@XStreamAlias("player")
public class Player implements IPlayer {
	
	private String myName;
	private PlayerDirection myDirection;
	private int myRow;
	private int myCol;
	private List<PlayerAttribute> myAttributes;
	private List<Item> myInventory;
	private List<Battle> myBattleHistory;
	private List<Interaction> myInteractionHistory;
	private List<Status> myStatus;
	
	public Player(String name, int row, int col) {
		myName = name;
		myDirection = PlayerDirection.NORTH;
		myRow = row;
		myCol = col;
		myAttributes = new ArrayList<PlayerAttribute>();
		myInventory = new ArrayList<Item>();
		myBattleHistory = new ArrayList<Battle>();
		myInteractionHistory = new ArrayList<Interaction>();
		myStatus = new ArrayList<Status>();
	}

	public String getPlayerName() {
		return myName;
	}

	public PlayerDirection getDirection() {
		return myDirection;
	}
	
	public int getRow() {
		return myRow;
	}
	
	public int getCol() {
		return myCol;
	}

	public List<PlayerAttribute> getAttributes() {
		return myAttributes;
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

	public void setDirection(PlayerDirection direction) {
		myDirection = direction;
	}

	public void setRow(int row) {
		myRow = row;
	}
	
	public void setCol(int col) {
		myCol = col;
	}

	public void addAttribute(PlayerAttribute attribute) {
		myAttributes.add(attribute);
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