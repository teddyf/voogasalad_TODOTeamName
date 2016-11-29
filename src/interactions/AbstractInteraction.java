package interactions;

import boardObjects.Block;
import boardObjects.EnemyBlock;
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
    private Player player;
    public AbstractInteraction(Player player){
        this.player = player;
    }
    public AbstractInteraction(){
        this.player = null;
    }
    protected Player getPlayer(){
        return player;
    }
    public abstract void act();
}
