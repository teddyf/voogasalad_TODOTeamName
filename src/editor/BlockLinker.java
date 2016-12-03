package editor;

import block.Block;


/**
 * This class provides the utility for determining if two blocks can be linked. It then links the blocks if they can be
 * linked.
 * @author Aninda Manocha, Filip Mazurek
 */

public class BlockLinker {

    public boolean linkBlocks(Block block1, Block block2) {
        return (block1.link(block2) || block2.link(block2));
    }

//    private boolean checkBlocks(Block block1, Block block2) {
//        if (block1.canLink() ^ block2.canLink()) {
//            if (block1 instanceof TeleportBlock && block2 instanceof TeleportBlock) {
//                if (block1 instanceof TeleportSendBlock) { //block1 is the sender
//
//                } else { //block2 is the sender
//
//                }
//            }
//            if (block1 instanceof SwitchGateBlock && block2 instanceof SwitchGateBlock) {
//                int a = 0;
//            }
//        }
//        return false;
//    }
//
//    private Block checkTeleportBlocks
}
