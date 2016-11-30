package interactions;

import block.Block;
import javafx.scene.text.Text;

public interface Interaction {
    void actOnStep();
    default void actOnTalk(String message){
        // do nothing
    }
}
