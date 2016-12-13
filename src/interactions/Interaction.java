package interactions;

import block.BlockUpdate;
import player.PlayerInstance;
import java.io.Serializable;
import java.util.List;

/**
 * All interactions are to be used as classes that are going to be composed with board ui.scenes.editor.objects.
 * These board ui.scenes.editor.objects will interact with the player as dictated by the interaction class(es)
 * with which they are composed.
 * @author Filip Mazurek, Aninda Manocha
 */

public interface Interaction extends Serializable {

    /**
     * Apply the interaction in the back end.
     *
     * @param player - the player object that interacts with a block
     * @return the list of changes which the front end needs to apply
     */
    List<BlockUpdate> act(PlayerInstance player);
}
