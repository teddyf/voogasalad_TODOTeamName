package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import api.IPlayer;
import editor.backend.Battle;
import editor.backend.Item;
import editor.backend.Status;
import interactions.Interaction;

/**
 * This is the class that holds all of the information corresponding to the
 * player
 * 
 * @author Aninda Manocha
 */
@XStreamAlias("player")
public class Player implements IPlayer {
	@XStreamOmitField
	private static final int DEFAULT_HEALTH = 100;
	private int battlesWon;
	private int battlesLost;
	private List<String> myNames;
	private String myPlayerName;
	private PlayerDirection myDirection;
	private int myRow;
	private int myCol;
	private int myGridIndex;
	private List<PlayerAttribute> myAttributes;
	private List<Item> myInventory;
	private List<Battle> myBattleHistory;
	private List<Interaction> myInteractionHistory;
	private List<Status> myStatus;
	
	private double health;

	public Player(List<String> names, String playerName, int row, int col, int gridIndex) {
		myNames = names;
		myPlayerName = playerName;
		myDirection = PlayerDirection.NORTH;
		myRow = row;
		myCol = col;
		myGridIndex = gridIndex;
		myAttributes = new ArrayList<>();
		myInventory = new ArrayList<>();
		myBattleHistory = new ArrayList<>();
		myInteractionHistory = new ArrayList<>();
		myStatus = new ArrayList<>();
		battlesWon = battlesLost = 0;
		health = DEFAULT_HEALTH;
	}
	public int getBattlesWon(){
		return battlesWon;
	}
	public int getBattlesLost(){
		return battlesLost;
	}
	public void incrementBattlesWon(){
		battlesWon++;
	}
	public void incrementBattlesLost(){
		battlesLost++;
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

	public List<PlayerAttribute> getAttributes() {
		return Collections.unmodifiableList(myAttributes);
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

	public boolean addAttribute(PlayerAttribute attribute) {
		for (PlayerAttribute playerAttribute : myAttributes) {
		    if (attribute.getName().equals(playerAttribute.getName())) {
		        return false;
            }
        }
	    myAttributes.add(attribute);
		return true;
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
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getHealth() {
		return health;
	}

	/***** GETTERS *****/

	/***** SETTERS *****/
}