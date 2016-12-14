package player;

/**
 * This class wraps an update when the grid shrinks and the player's location needs to be updated.
 * @author Aninda Manocha
 */

public class PlayerBlockUpdate {

    private PlayerUpdate myUpdate;
    private int myOffset;

    public PlayerBlockUpdate(PlayerUpdate update, int offset) {
        myUpdate = update;
        myOffset = offset;
    }

    /**
     * Gets the property of the player that was updated (either the row or column) when the grid shrinks
     * @return the player property
     */
    public PlayerUpdate getUpdate() {
        return myUpdate;
    }

    /**
     * Gets the amount by which the player property changes (the offset)
     * @return the offset
     */
    public int getOffset() {
        return myOffset;
    }
}
