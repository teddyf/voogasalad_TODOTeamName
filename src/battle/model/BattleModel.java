package battle.model;

import java.util.Observable;
import battle.controller.BattleModelInView;
import block.EnemyBlock;
import player.Player;

/**
 * @author Daniel Chai
 */
public class BattleModel extends Observable implements BattleModelInView {
	private Player player;
	private EnemyBlock enemy;

	private boolean playerWon = false;
	private boolean playerLost = false;

	public BattleModel(Player player, EnemyBlock enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	public double getPlayerHP() {
		return player.getHealth();
	}

	@Override
	public double getEnemyHP() {
		return enemy.getHealth();
	}

	@Override
	public void setPlayerHP(double playerHP) {
		if (playerHP <= 0) {
			playerLost = true;
		}

		player.setHealth(playerHP);
		setChanged();
		notifyObservers();
	}

	@Override
	public void setEnemyHP(double enemyHP) {
		if (enemyHP <= 0) {
			playerWon = true;
		}

		enemy.setHealth(enemyHP);
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean checkPlayerWon() {
		return playerWon;
	}

	@Override
	public boolean checkPlayerLost() {
		return playerLost;
	}
	
	@Override
	public void addBattleWon() {
		player.incrementBattlesWon();
	}

	@Override
	public void addBattleLost() {
		player.incrementBattlesLost();
	}
}
