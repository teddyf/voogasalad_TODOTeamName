package model.block.blocktypes;

import model.block.AbstractBlock;

/**
 * Created by Harshil Garg on 12/8/16.
 */

public class GroundBlock extends AbstractBlock {

    public GroundBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }

}
