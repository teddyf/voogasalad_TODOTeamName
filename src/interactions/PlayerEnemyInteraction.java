package interactions;

import boardObjects.EnemyBlock;
import editor.backend.Item;
import player.Player;

/**
 * Created by Bill Xiong on 11/20/16.
 * defines interactions betwen player and enemy
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
