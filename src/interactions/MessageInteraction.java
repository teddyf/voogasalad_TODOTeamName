package interactions;

import block.Block;
import block.BlockUpdate;
import block.BlockUpdateType;
import player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Interaction class which will give a message to the front end to display.
 * @author Filip Mazurek, Aninda Manocha
 */
public class MessageInteraction implements TalkInteraction {
    private int myRow;
    private int myCol;

    public MessageInteraction(int row, int col) {
        myRow = row;
        myCol = col;
    }

    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.DISPLAY_MESSAGE, myRow, myCol));
        return updateList;
    }

}
