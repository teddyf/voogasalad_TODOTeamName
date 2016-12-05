package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * Specific switch interaction for the touch switches.
 * @author Filip Mazurek, Aninda Manocha
 */
public class SwitchTouchInteraction extends SwitchInteraction implements TalkInteraction{

    public SwitchTouchInteraction(SwitchBlock switchBlock) {
        super(switchBlock);
    }

}
