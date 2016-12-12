package interactions;

import block.BlockUpdate;
import block.BlockUpdateType;
import block.NPCBlock;
import block.NPCDirection;
import player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Interaction so that NPC's may turn to face the player character
 *
 * @author Filip Mazurek
 */
public class NPCTurnToFaceInteraction implements Interaction {
    private NPCBlock myNPCBlock;

    public NPCTurnToFaceInteraction(NPCBlock block) {
        myNPCBlock = block;
    }

    public List<BlockUpdate> act(Player player) {
        List<BlockUpdate> updatesList = new ArrayList<>();

        String status = "";

        switch (player.getDirection()) {
            case NORTH:
                myNPCBlock.switchDirection(NPCDirection.DOWN);
                status = "down";
                break;
            case SOUTH:
                myNPCBlock.switchDirection(NPCDirection.UP);
                status = "up";
                break;
            case EAST:
                myNPCBlock.switchDirection(NPCDirection.LEFT);
                status = "left";
                break;
            case WEST:
                myNPCBlock.switchDirection(NPCDirection.RIGHT);
                status = "right";
                break;
        }

        String newName = myNPCBlock.replaceNameStatus(myNPCBlock.getName(), status);
        updatesList.add(new BlockUpdate(BlockUpdateType.RE_RENDER, myNPCBlock.getRow(), myNPCBlock.getCol(), newName));
        return updatesList;
    }
}
