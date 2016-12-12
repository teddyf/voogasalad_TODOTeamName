package xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import grid.GridManager;
import player.Player;

/**
 * Wrapper class on a GridManager and Player used for XStream.
 * 
 * @author Daniel Chai
 */

@XStreamAlias("root")
public class GridWorldAndPlayer {
	
	@XStreamAlias("gridManager")
	private GridManager myGridManager;
	
	@XStreamAlias("player")
	private Player myPlayer;
	
	public GridWorldAndPlayer(GridManager gridManager, Player player) {
		myGridManager = gridManager;
		myPlayer = player;
	}
	
	public GridManager getGridWorld() {
		return myGridManager;
	}
	
	public Player getPlayer() {
		return myPlayer;
	}
}
