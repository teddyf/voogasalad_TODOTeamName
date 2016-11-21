package interactions;

import boardObjects.Enemy;
import editor.backend.Item;
import player.Player;

/**
 * defines interactions between player and enemy
 * @author Bill Xiong
 */
public class PlayerEnemyInteraction extends AbstractInteraction{
    private Enemy enemy;
    private Player player;
    public PlayerEnemyInteraction(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }
    public void act(){

    }

}
