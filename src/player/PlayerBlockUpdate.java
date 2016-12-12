package player;

/**
 * This class wraps an update when the grid shrinks and the player's location needs to be updated.
 * @author Aninda Manocha
 */

public class PlayerBlockUpdate {
    private PlayerUpdate myUpdate;
    private int myOffset;

    public PlayerBlockUpdate(PlayerUpdate myUpdate, int offset) {
        myUpdate = myUpdate;
        myOffset = offset;
    }

    public PlayerUpdate getUpdate() {
        return myUpdate;
    }

    public int getOffset() {
        return myOffset;
    }
}
