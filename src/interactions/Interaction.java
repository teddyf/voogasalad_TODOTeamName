package interactions;

import player.Player;

/**
 * All interactions are to be used as classes that are going to be composed with board objects.
 * These board objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 *
 * The topmost and default method of interaction for all interactions. Whenever a child specific interaction does not
 * implement the desired methods, they will default to not doing anything.
 * @author Filip Mazurek
 */
public abstract class Interaction {
    public void actOnStep(Player player) {
        // do nothing
    }
    public void actOnTalk(Player player){
        // do nothing
    }
}
