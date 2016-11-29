package interactions;

import block.Block;
import block.EnemyBlock;
import editor.backend.Interaction;
import editor.backend.Item;
import player.Player;

/**
 * All interactions are to be used as classes that are going to be composed with board objects.
 * These board objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 *
 * @author Filip Mazurek
 */
public abstract class AbstractInteraction implements Interaction{
    //enums hold default interactions for users to choose from. 
    public enum BlockPlayer{

    }
    public enum ItemEnemy{

    }
    public enum ItemPlayer{

    }
    public enum PlayerEnemy{

    }
    protected void defaultBlockPlayer(Block block, Player player){

    }
    protected void defaultItemEnemy(Item item, EnemyBlock enemy){

    }
    protected void defaultItemPlayer(Item item, Player player){

    }
    protected void defaultPlayerEnemy(Player player, EnemyBlock enemy){

    }
    public abstract void act();
}
