package editor.backend;

import java.util.HashSet;

public interface Player {
	public HashSet<Item> getInventory();
	public HashSet<Battle> getBattleHistory();
	public HashSet<Interaction> getInteractionHistory();
	public int getRow();
	public int getCol();
	public HashSet<Status> getPlayerStatus();
}