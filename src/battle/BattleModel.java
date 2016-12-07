package battle;

import block.EnemyBlock;
import player.Player;
import player.PlayerAttribute;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleModel {
	private static final String PLAYER_HP = "PLAYER_HP";
	
	private Player player;
	private EnemyBlock enemy;
	
	public BattleModel(Player player, EnemyBlock enemy, int playerHP, int enemyHP) {
		this.player = player;
		this.enemy = enemy;
		
		setPlayerHP(playerHP);
		setEnemyHP(enemyHP);
	}
	
	public int getPlayerHP() {
		return (int) player.getAttribute(PLAYER_HP).getAmount();
	}
	
	public int getEnemyHP() {
		return enemy.getHealth();
	}
	
	private void setPlayerHP(int playerHP) {
		PlayerAttribute attr = new PlayerAttribute(PLAYER_HP, playerHP);
		player.addAttribute(PLAYER_HP, attr);
	}
	
	private void setEnemyHP(int enemyHP) {
		enemy.setHealth(enemyHP);
	}
}
