package block;

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
    OBSTACLE,
    PLAYER_SPAWN,
    PORTAL,
    SWITCH_FLOOR,
    SWITCH_TOUCH,
    TELEPORT_RECEIVE,
    TELEPORT_SEND,

    ITEM_KEY
}