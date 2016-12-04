package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * Specific switch interaction for the touch switches.
 * @author Filip Mazurek
 */
public class SwitchTouchInteraction extends SwitchInteraction{

    public SwitchTouchInteraction(SwitchBlock sBlock) {
        super(sBlock);
    }

    @Override
    public void actOnTalk(Player player) {
        act();
    }
}
