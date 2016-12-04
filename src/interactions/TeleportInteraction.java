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

    public TeleportInteraction(int row, int col) {
        myDestinationRow = row;
        myDestinationCol = col;
    }

    @Override
    public List<BlockUpdate> act(Player player) {
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
        return new ArrayList<BlockUpdate>();
    }
}
