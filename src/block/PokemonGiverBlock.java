package block;

import interactions.DisappearInteraction;
import interactions.ReceivePokemonInteraction;

/**
 * @author Filip Mazurek
 */
public class PokemonGiverBlock extends CommunicatorBlock {

    public PokemonGiverBlock(String name, int row, int col) {
        super(name, row, col);
        addTalkInteraction(new ReceivePokemonInteraction());
        addTalkInteraction(new DisappearInteraction(this));
    }

}
