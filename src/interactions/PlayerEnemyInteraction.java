package interactions;

import boardObjects.Enemy;
import editor.backend.Item;
import player.Player;

/**
 * Created by Bill Xiong on 11/20/16.
 * defines interactions betwen player and enemy
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
