package interactions;

import block.BlockUpdate;
import block.BlockUpdateType;
import player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Interaction which triggers the successful end of the current game.
 *
 * @author Filip Mazurek,
 */
public class WinInteraction implements Interaction {

    @Override
    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.WIN_GAME, player.getRow(), player.getCol(), ""));
        return updateList;
    }
}
