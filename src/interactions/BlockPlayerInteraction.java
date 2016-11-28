package interactions;

import boardObjects.Block;
import editor.backend.Item;
import player.Player;

public class BlockPlayerInteraction extends AbstractInteraction{

    private Block block;
    private Player player;
    public BlockPlayerInteraction(Block block, Player player) {
        this.block = block;
        this.player = player;
    }

    public void act(){
        defaultBlockPlayer(block, player);
    }
}