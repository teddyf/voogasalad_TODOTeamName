package model.interactions;

import api.Player;
import model.block.BlockUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mazurek
 */
public class ReceivePokemonInteraction implements Interaction {

    public List<BlockUpdate> act(Player player) {
        player.incrementNumPokemon();
        return new ArrayList<>();
    }
}
