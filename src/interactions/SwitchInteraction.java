package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * The interaction that allows toggling of gates whenever the switch is used.
 *
 * @author Filip Mazurek
 */
public class SwitchInteraction extends AbstractInteraction {
    private SwitchBlock switchBlock;
    public SwitchInteraction(SwitchBlock block){
        switchBlock = block;
    }
    public void act() {
        //add event handler to the group

    }
}
