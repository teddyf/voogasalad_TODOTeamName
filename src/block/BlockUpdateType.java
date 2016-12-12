package block;

/**
 * The type of update which will be applied by the BlockUpdate class. Enum to decide on which specific updates are
 * allowed.
 *
 * @author Filip Mazurek, Aninda Manocha
 */
public enum BlockUpdateType {
    BATTLE,
    DISPLAY_MESSAGE,
    RE_RENDER,
    TELEPORT,
    WIN_GAME
}
