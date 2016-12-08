package battle;

/**
 * Created by Bill Xiong on 12/7/16.
 * 
 * @author Bill Xiong
 */
public class PlayerView extends ItemView {
	private static final String PLAYER_NAME = "player";
	public PlayerView(int hp, int x, int y) {
		super(PLAYER_NAME, hp, x, y);
	}
}
