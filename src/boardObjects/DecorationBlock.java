package boardObjects;

import grid.BlockType;

/**
 * A board object which should have no interaction with the player character.
 *
 * @author Filip Mazurek
 */
public class DecorationBlock extends Block implements Walkable{
    private static final BlockType myBlockType= BlockType.DECORATION;


    public DecorationBlock(String name) {
        super(name);
        setWalkableStatus(IS_WALKABLE);
    }



}
