package interactions;

import block.BlockUpdate;
import player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the player to be teleported to a destination block from a teleporter.
 * @author Filip Mazurek, Aninda Manocha
 */

public class TeleportInteraction implements StepInteraction {
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
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
        player.setGridIndex(myDestinationGrid);
        return new ArrayList<BlockUpdate>();
    }
}
