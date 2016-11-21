package interactions;

import boardObjects.Enemy;
import editor.backend.Item;
import player.Player;

/**
 * defines interactions between item and enemies
 * @author Bill Xiong
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
