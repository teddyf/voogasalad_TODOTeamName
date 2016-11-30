package interactions;

import block.Block;
import block.EnemyBlock;
import interactions.Interaction;
import editor.backend.Item;
import player.Player;
import player.PlayerAttribute;

/**
 * All interactions are to be used as classes that are going to be composed with board objects.
 * These board objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 * @author Filip Mazurek
 */
public abstract class AbstractInteraction implements Interaction{
    private PlayerAttribute player;

    //enums hold default interactions for users to choose from.
    //default methods
    private Interaction increaseAttribute;
    private Interaction decreaseAttribute;
    private Interaction communicate;
    private Interaction doNothing;
    private Interaction currentAction;
    public AbstractInteraction(PlayerAttribute player){
        this.player = player;
        increaseAttribute = player::increase;
        decreaseAttribute = player::decrease;
        //TODO change communicate later
        communicate = () -> {};
        doNothing = () -> {};
        currentAction = doNothing;
    }
    public AbstractInteraction(){
        this.player = null;
    }
    protected Interaction getCurrentAction(){
        return currentAction;
    }
    public void setCurrentAction(Interaction action){
        currentAction = action;
    }
    protected PlayerAttribute getPlayer(){
        return player;
    }
    public abstract void act();
}
