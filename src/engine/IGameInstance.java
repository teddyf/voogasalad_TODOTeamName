package engine;

import engine.backend.GameStatus;
import player.Player;

public interface IGameInstance {
	
	public int getScore();
	
	public Player getPlayer();
	
	public void savePlayer();
	
	public Player loadPlayer();
	
	public GameStatus getGameStatus();
	
	public void resetPlayer();
}
