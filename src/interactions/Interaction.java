package interactions;

import player.Player;

/**
 * All interactions are to be used as classes that are going to be composed with board objects.
 * These board objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 *
 * @author Filip Mazurek, Aninda Manocha
 */

public interface Interaction {
    void act(Player player);
}
