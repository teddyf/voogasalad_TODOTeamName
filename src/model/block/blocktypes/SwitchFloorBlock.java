package model.block.blocktypes;

import model.interactions.SwitchInteraction;

/**
 * A switch that is activated by being stepped on by the model.player character.
 *
 * @author Filip Mazurek
 */
public class SwitchFloorBlock extends SwitchBlock {

    public SwitchFloorBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
        addStepInteraction(new SwitchInteraction(this));
    }
}
