package block;

/**
 * Allows observer to know what to do with the block as something happens to it
 *
 * @author Filip Mazurek, Aninda Manocha
 */
public enum BlockUpdateType {
    BATTLE,
    DISPLAY_MESSAGE,
    RE_RENDER,
    WIN_GAME
}
