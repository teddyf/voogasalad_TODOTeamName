package interactions;

import block.SwitchBlock;
import player.Player;

/**
 * Specific switch interaction for the floor switches.
 * @author Filip Mazurek, Aninda Manocha
 */

public class SwitchFloorInteraction extends SwitchInteraction implements StepInteraction{

    public SwitchFloorInteraction(SwitchBlock switchBlock) {
        super(switchBlock);
    }
}