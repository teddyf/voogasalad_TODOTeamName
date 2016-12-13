package xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import grid.GridWorld;
import player.PlayerInstance;

/**
 * Wrapper class on a GridManager and Player used for XStream.
 * 
 * @author Daniel Chai
 */

@XStreamAlias("root")
public class GridWorldAndPlayer {
	
	@XStreamAlias("gridWorld")
	private GridWorld myGridWorld;
	
	@XStreamAlias("player")
	private PlayerInstance myPlayer;
	
	public GridWorldAndPlayer(GridWorld gridWorld, PlayerInstance player) {
		myGridWorld = gridWorld;
		myPlayer = player;
	}
	
	public GridWorld getGridWorld() {
		return myGridWorld;
	}
	
	public PlayerInstance getPlayer() {
		return myPlayer;
	}
}
