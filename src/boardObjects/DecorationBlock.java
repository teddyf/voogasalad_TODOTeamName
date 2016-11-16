package boardObjects;

import grid.BlockType;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek
 */
public class DecorationBlock extends Block {
    private static final BlockType myBlockType= BlockType.DECORATION;


    public DecorationBlock(String name) {
        super(name);
        makeWalkable();
    }



}
