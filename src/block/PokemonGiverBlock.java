package block;

import interactions.DisappearInteraction;
import interactions.Interaction;
import interactions.ReceivePokemonInteraction;

/**
 * @author Filip Mazurek
 */
public class PokemonGiverBlock extends CommunicatorBlock {

    public PokemonGiverBlock(String name, int row, int col) {
        super(name, row, col);
        if(!this.isWalkable()){
            addTalkInteraction(new ReceivePokemonInteraction());
        }
        else{
            for(Interaction i : getTalkInteractions()){
                if(i instanceof ReceivePokemonInteraction){
                    removeTalkInteraction(i);
                }
            }
        }
        addTalkInteraction(new DisappearInteraction(this));
    }

}
