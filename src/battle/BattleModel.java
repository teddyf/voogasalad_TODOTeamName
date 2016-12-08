package battle;

import java.util.Observable;

import block.EnemyBlock;
import player.Player;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleModel extends Observable implements BattleModelInView {
	private Player player;
	private EnemyBlock enemy;
	
	public BattleModel(Player player, EnemyBlock enemy) {
		this.player = player;
		this.enemy = enemy;
	}
	
	@Override
	public int getPlayerHP() {
		return player.getHealth();
	}
	
	@Override
	public int getEnemyHP() {
		return enemy.getHealth();
	}
	
	@Override
	public void setPlayerHP(int playerHP) {
		player.setHealth(playerHP);
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void setEnemyHP(int enemyHP) {
		enemy.setHealth(enemyHP);
		setChanged();
		notifyObservers();
	}
}
