package interactions;

import block.Block;
import javafx.scene.text.Text;
import player.Player;

public interface Interaction {
    void actOnStep(Player player);
    default void actOnTalk(String message){
        // do nothing
    }
}
