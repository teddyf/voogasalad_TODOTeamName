package interactions;

import block.EnemyBlock;
import editor.backend.Item;

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
        defaultItemEnemy(item, enemy);
    }
}
