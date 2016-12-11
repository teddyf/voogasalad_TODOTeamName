package interactions;

import block.BlockUpdate;
import player.Player;

import java.util.List;

/**
 * All interactions are to be used as classes that are going to be composed with board ui.scenes.editor.objects.
 * These board ui.scenes.editor.objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 *
 * @author Filip Mazurek, Aninda Manocha
 */

public interface Interaction {

    /**
     * Apply the interaction in the back end.
     *
     * @param player: give the current player object in case it needs to be modified.
     * @return the list of changes which the front end needs to apply.
     */
    List<BlockUpdate> act(Player player);
}
