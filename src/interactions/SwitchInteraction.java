package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * Add ability for a switch to interact with a gate to open it. This will for the gate to become walkable in the back
 * end, as well as force the front end to redraw the gate so that the user may see the change.
 * @author Filip Mazurek
 */
public abstract class SwitchInteraction implements Interaction{
    SwitchBlock mySwitchBlock;

    public SwitchInteraction(SwitchBlock switchBlock) {
        mySwitchBlock = switchBlock;
    }

    public void act(Player player) {
        mySwitchBlock.toggleGates();
    }
}
