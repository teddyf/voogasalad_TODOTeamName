package interactions;

import player.Player;


public interface Interaction {
    void actOnStep(Player player);
    default void actOnTalk(String message){
        // do nothing
    }
}
