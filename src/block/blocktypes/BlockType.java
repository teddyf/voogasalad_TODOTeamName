package block.blocktypes;

/**
 * Enumeration to allow easier communication between the front end and the back end. Specifies the exact types of blocks
 * we have implemented.
 *
 * @author Filip Mazurek
 */
public enum BlockType {
    COMMUNICATOR,
    DECORATION,
    ENEMY,
    GATE,
    GROUND,
    ITEM,
    ITEM_KEY,
    NPC,
    OBSTACLE,
    POKEMON_GIVER,
    SWITCH_FLOOR,
    SWITCH_TOUCH,
    TELEPORT,
    WIN_STEP,
    WIN_TALK,
}