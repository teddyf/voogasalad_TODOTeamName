package battle;

import block.EnemyBlock;
import player.Player;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleModel {
	private Player player;
	private EnemyBlock enemy;
	
	public BattleModel(Player player, EnemyBlock enemy) {
		this.player = player;
		this.enemy = enemy;
	}
	
	public int getPlayerHP() {
		return player.getHealth();
	}
	
	public int getEnemyHP() {
		return enemy.getHealth();
	}
	
	public void setPlayerHP(int playerHP) {
		player.setHealth(playerHP);
	}
	
	public void setEnemyHP(int enemyHP) {
		enemy.setHealth(enemyHP);
	}
}
