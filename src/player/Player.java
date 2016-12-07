package player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
	@XStreamOmitField
	private static final int DEFAULT_HEALTH = 100;
	
	private String myName;
	private PlayerDirection myDirection;
	private int myRow;
	private int myCol;
	private int myGridIndex;
	private Map<String, PlayerAttribute> myAttributes;
	private List<Item> myInventory;
	private List<Battle> myBattleHistory;
	private List<Interaction> myInteractionHistory;
	private List<Status> myStatus;
	private int health;
	
	public Player(String name, int row, int col, int gridIndex) {
		myName = name;
		myDirection = PlayerDirection.NORTH;
		myRow = row;
		myCol = col;
		myGridIndex = gridIndex;
		myAttributes = new HashMap<>();
		myInventory = new ArrayList<>();
		myBattleHistory = new ArrayList<>();
		myInteractionHistory = new ArrayList<>();
		myStatus = new ArrayList<>();
		
		health = DEFAULT_HEALTH;
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

	public int getGridIndex() {
		return myGridIndex;
	}

	public Collection<PlayerAttribute> getAttributes() {
		return myAttributes.values();
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

	public void setGridIndex(int gridIndex) {
		myGridIndex = gridIndex;
	}

	public void addAttribute(String key, PlayerAttribute attribute) {
		myAttributes.put(key, attribute);
	}
	
	public PlayerAttribute getAttribute(String key) {
		return myAttributes.get(key);
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
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
}