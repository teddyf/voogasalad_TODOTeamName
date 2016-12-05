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
}
