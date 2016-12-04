package interactions;

import player.Player;

/**
 * Allows the player to be teleported to a destination block from a teleporter.
 * @author Filip Mazurek
 */
public class TeleportInteraction implements StepInteraction {
    private int myDestinationRow;
    private int myDestinationCol;

    public TeleportInteraction(int row, int col) {
        myDestinationRow = row;
        myDestinationCol = col;
    }

    @Override
    public void act(Player player) {
        player.setRow(myDestinationRow);
        player.setCol(myDestinationCol);
    }
}
