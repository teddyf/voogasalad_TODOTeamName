package interactions;

import boardObjects.Enemy;
import editor.backend.Item;
import player.Player;

/**
 * Created by Bill Xiong on 11/20/16.
 * defines interactions between item and enemies
 */
public class ItemEnemyInteraction extends AbstractInteraction{
    private Item item;
    private Enemy enemy;
    public ItemEnemyInteraction(Item item, Enemy enemy) {
        this.item = item;
        this.enemy = enemy;
    }

    public void act(){

    }
}
