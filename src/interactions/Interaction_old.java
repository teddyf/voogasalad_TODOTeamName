package interactions;

import player.Player;


public interface Interaction_old {
    void actOnStep(Player player);
    default void actOnTalk(String message){
        // do nothing
    }
}
