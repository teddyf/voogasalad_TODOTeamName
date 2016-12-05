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
    List<BlockUpdate> act(Player player);
}
