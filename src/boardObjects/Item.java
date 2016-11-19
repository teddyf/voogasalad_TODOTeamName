package boardObjects;

import interactions.AbstractInteraction;
import player.Player;

/**
 * Created by billxiong24 on 11/18/16.
 * interfaces for all items the user uses, such as potions, pokeballs, etc.
 */
public interface Item{
    public void act();
    public void defineInteractions(AbstractInteraction interaction);
    public Player owner();
}
