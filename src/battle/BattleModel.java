package battle;

import java.util.Observable;

import block.EnemyBlock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import player.Player;

/**
 * @author Daniel Chai, Bill Xiong
 */
public class BattleModel extends Observable implements BattleModelInView {
	private static final int TIME_STEP = 1000;
	
	private Player player;
	private EnemyBlock enemy;
	private Timeline timeline;
	
	private boolean playerWon = false;
	private boolean playerLost = false;
	
	public BattleModel(Player player, EnemyBlock enemy) {
		this.player = player;
		this.enemy = enemy;
		
		initTimeline();
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
		if (playerHP <= 0) {
			timeline.stop();
			playerLost = true;
		}
		
		player.setHealth(playerHP);
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void setEnemyHP(int enemyHP) {
		if (enemyHP <= 0) {
			timeline.stop();
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
	
	private void initTimeline() {
		timeline = new Timeline();
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(TIME_STEP), 
	    		e -> setPlayerHP(getPlayerHP() - BattleView.DAMAGE)));
	    timeline.play();
	}	
}
