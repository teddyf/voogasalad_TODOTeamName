package editor.backend;

import player.Player;

public interface GameInstance {
	public int getScore();
	public Player getPlayer();
	public void savePlayer();
	public Player loadPlayer();
	public GameStatus getGameStatus();
	public void resetPlayer();
}
