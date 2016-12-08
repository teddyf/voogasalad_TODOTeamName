package block;

/**
 * This block helps the frontend determine where the player is first spawned when beginning a game. Only one player
 * spawn can exist in the game instance at a time.
 * @author Aninda Manocha, Filip Mazurek
 */

public class PlayerSpawnBlock extends DecorationBlock {
    public PlayerSpawnBlock(String name, int row, int col) {
        super(name, row, col);
    }

    // TODO: PlayerSpawnBlock is completely unnecessary in the back end. However, it will still help the front end to
    // TODO  keep track of where it is placed. The front end should detect if a new Block of BlockType PLAYER_SPAWN
    // TODO  is placed on the board. Then, the old one should be replaced with a DECORATION type block.

    // TODO  This block is extremely important as it must tell the engine with which grid it must start.


    // TODO back end
    /**
     * refactor
     * custom error messages on bad block placement (B
     */
}
