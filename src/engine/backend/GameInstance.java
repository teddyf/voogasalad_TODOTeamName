package engine.backend;

import editor.backend.Player;

public interface GameInstance {
	public int getScore();
	public Player getPlayer();
	public void savePlayer();
	public Player loadPlayer();
	public GameStatus getGameStatus();
	public void resetPlayer();
}
