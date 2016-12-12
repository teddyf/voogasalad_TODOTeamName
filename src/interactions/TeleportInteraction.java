package interactions;

import block.BlockUpdate;
import block.BlockUpdateType;
import player.Player;
import player.PlayerUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Allows the player to be teleported to a destination block from a teleporter.
 *
 * @author Filip Mazurek, Aninda Manocha
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
    public List<BlockUpdate> act(Player player) {
        int originalRow = player.getRow();
        int originalCol = player.getCol();
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
        player.setGridIndex(myDestinationGrid);
        List<BlockUpdate> updateList = new ArrayList<>();
        updateList.add(new BlockUpdate(BlockUpdateType.TELEPORT, myDestinationRow - originalRow,
                myDestinationCol - originalCol, "teleport"));
        return updateList;
    }
}
