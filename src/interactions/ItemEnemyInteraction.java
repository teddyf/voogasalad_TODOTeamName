package interactions;

import boardObjects.EnemyBlock;
import editor.backend.Item;
import player.Player;

/**
 * defines interactions between item and enemies
 * @author Bill Xiong
 */
public class ItemEnemyInteraction extends AbstractInteraction{
    private Item item;
    private EnemyBlock enemy;
    public ItemEnemyInteraction(Item item, EnemyBlock enemy) {
        this.item = item;
        this.enemy = enemy;
    }

    public void act(){

    }
}
