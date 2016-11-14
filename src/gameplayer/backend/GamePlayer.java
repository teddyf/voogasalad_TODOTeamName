package gameplayer.backend;

import java.util.HashSet;

import editor.backend.Game;

public interface GamePlayer {
	public void playGame(String name);
	public void removeGame(String name);
	public HashSet<Game> getGames();
}
