package interactions;

import block.Block;
import block.BlockUpdate;
import block.BlockUpdateType;
import block.DecorationBlock;
import player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mazurek
 */
public class DisappearInteraction implements Interaction {
    private Block myBlock;

    //TODO: put in resources, random ext generation
    private String DEFAULT = "resources/images/tiles/ground/grass-1.png";

    public DisappearInteraction(Block block) {
        myBlock = block;
    }

    @Override
    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updatesList = new ArrayList<>();
        int myRow = myBlock.getRow();
        int myCol = myBlock.getCol();

        myBlock.setWalkableStatus(true);
        myBlock = new DecorationBlock(DEFAULT, myRow, myCol);
        updatesList.add(new BlockUpdate(BlockUpdateType.RE_RENDER, myBlock.getRow(), myBlock.getCol(), DEFAULT));
        myBlock.setWalkableStatus(true);

        // TODO: set to a new decoration block in the grid
        return updatesList;
    }
}
