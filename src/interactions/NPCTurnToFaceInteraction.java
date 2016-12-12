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

        int extensionLoc = myNPCBlock.getName().lastIndexOf('.');
        String extension = myNPCBlock.getName().substring(extensionLoc);
        int statusLoc = myNPCBlock.getName().lastIndexOf('-');
        String status = "";

        switch (player.getDirection()) {
            case NORTH:
                myNPCBlock.switchDirection(NPCDirection.DOWN);
                status = "up";
                break;
            case SOUTH:
                myNPCBlock.switchDirection(NPCDirection.UP);
                status = "down";
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

        String newName = myNPCBlock.getName().substring(0, statusLoc + 1) + status + extension;
        updatesList.add(new BlockUpdate(BlockUpdateType.RE_RENDER, myNPCBlock.getRow(), myNPCBlock.getCol(), newName));
        return updatesList;
    }
}
