package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * Specific switch interaction for the floor switches.
 * @author Filip Mazurek
 */
public class SwitchFloorInteraction extends SwitchInteraction{

    public SwitchFloorInteraction(SwitchBlock sBlock) {
        super(sBlock);
    }

    @Override
    public void actOnStep(Player player) {
        act();
    }
}
