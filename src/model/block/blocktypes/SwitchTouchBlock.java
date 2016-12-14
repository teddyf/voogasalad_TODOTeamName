package model.block.blocktypes;

import model.interactions.SwitchInteraction;

/**
 * A switch that is activated on pressing the main interaction button by the model.player character.
 *
 * @author Filip Mazurek
 */
public class SwitchTouchBlock extends SwitchBlock {

    public SwitchTouchBlock(String name, int row, int col) {
        super(name, row, col);
        addTalkInteraction(new SwitchInteraction(this));
    }
}
