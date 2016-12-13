package interactions;

import block.BlockUpdate;
import block.BlockUpdateType;
import player.PlayerInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the player to be teleported to a destination block from a teleporter.
 *
 * @author Filip Mazurek, Aninda Manocha, Harshil Garg
 */

public class TeleportInteraction implements Interaction {
    private int myDestinationRow;
    private int myDestinationCol;
    private int myDestinationGrid;

    public TeleportInteraction(int row, int col, int grid) {
        myDestinationRow = row;
        myDestinationCol = col;
        myDestinationGrid = grid;
    }

    @Override
    public List<BlockUpdate> act(PlayerInstance player) {
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.TELEPORT, myDestinationRow - player.getRow(), myDestinationCol - player.getCol(), "teleport"));
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
        player.setGridIndex(myDestinationGrid);
        return updateList;
    }
}
