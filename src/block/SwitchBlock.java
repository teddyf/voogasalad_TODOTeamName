package block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A top-level switch class which may interact with board ui.scenes.editor.objects which respond to a switch.
 *
 * @author Filip Mazurek, Aninda Manocha
 */

public abstract class SwitchBlock extends Block {
    private Set<GateBlock> myGates;

    public SwitchBlock(String name, int row, int col) {
        super(name, row, col);
        myGates = new HashSet<>();
    }

    public List<BlockUpdate> toggleGates() {
        List<BlockUpdate> blockUpdates = new ArrayList<>();
        for(GateBlock oneGate : myGates) {
            blockUpdates.add(oneGate.toggleOpenStatus());

        }
        return blockUpdates;
    }

    @Override
    public boolean link(Block block) {
        if(block instanceof GateBlock) {
            myGates.add((GateBlock) block);
            return true;
        }
        return false;
    }

    @Override
    public boolean unlink(Block block) {
        if (block instanceof GateBlock) {
            myGates.remove(block);
            return true;
        }
        return false;
    }
}
