package battle;

/**
 * Interface to expose select methods from the Model to the View.
 * 
 * @author Daniel Chai
 */
public interface BattleModelInView {
	public int getPlayerHP();
	
	public int getEnemyHP();
	
	public void setPlayerHP(int playerHP);
	
	public void setEnemyHP(int enemyHP);
}
