package interactions;

import block.SwitchBlock;

/**
 * Add ability for a switch to interact with a gate to open it. This will for the gate to become walkable in the back
 * end, as well as force the front end to redraw the gate so that the user may see the change.
 * @author Filip Mazurek
 */
public class SwitchInteraction extends Interaction {
    SwitchBlock mySwitchBlock;

    public SwitchInteraction(SwitchBlock sBlock) {
        mySwitchBlock = sBlock;
    }

    protected void act() {
        mySwitchBlock.toggleGates();
    }
}
