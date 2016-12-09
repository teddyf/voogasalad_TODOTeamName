package battle.controller;

/**
 * Interface to expose select methods from the Model to the View.
 * 
 * @author Daniel Chai
 */
public interface BattleModelInView {
	int getPlayerHP();

	int getEnemyHP();

	void setPlayerHP(int playerHP);

	void setEnemyHP(int enemyHP);

	boolean checkPlayerWon();

	boolean checkPlayerLost();
}
