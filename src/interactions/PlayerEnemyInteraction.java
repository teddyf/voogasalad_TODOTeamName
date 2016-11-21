package interactions;

import boardObjects.EnemyBlock;
import editor.backend.Item;
import player.Player;

/**
 * defines interactions between player and enemy
 * @author Bill Xiong
 */
public class PlayerEnemyInteraction extends AbstractInteraction{
    private EnemyBlock enemy;
    private Player player;
    public PlayerEnemyInteraction(EnemyBlock enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }
    public void act(){

    }

}
