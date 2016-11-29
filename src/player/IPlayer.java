package player;

import java.util.List;

import editor.backend.Battle;
import interactions.Interaction;
import editor.backend.Item;
import editor.backend.Status;

/*
 * The player interface 
 * 
 * @author Aninda Manocha
 */

public interface IPlayer {
	
	/**
	 * Gets the name of the player
	 * @return the name
	 */
	public String getPlayerName();
	
	/**
	 * Gets the row that the player is located in
	 * @return the row
	 */
	public int getRow();
	
	/**
	 * Gets the column that the player is located in
	 * @return the column
	 */
	public int getCol();
	
 	/**
	 * Gets the list of all items that the player possesses
	 * @return the list of items
	 */
	public List<Item> getInventory();
	
	/**
	 * Gets the list of all the battles that the player has had
	 * @return the list of battles
	 */
	public List<Battle> getBattleHistory();
	
	/**
	 * Gets the list of all the interactions that the player has had
	 * @return the list of interactions
	 */
	public List<Interaction> getInteractionHistory();
	
	/**
	 * Gets the list of all statuses that the player has
	 * @return the list of statuses
	 */
	public List<Status> getPlayerStatus();
	
	/**
	 * Adds an item to the inventory 
	 * @param item - the item to add
	 */
	public void addItem(Item item);
	
	/**
	 * Adds a battle to the battle history
	 * @param battle - the battle to add
	 */
	public void addBattle(Battle battle);
	
	/**
	 * Adds an interaction to the interaction history
	 * @param interaction - the interaction to add
	 */
	public void addInteraction(Interaction interaction);
	
	/**
	 * Adds a status to the list of statuses
	 * @param status - the status to add
	 */
	public void addStatus(Status status);
}