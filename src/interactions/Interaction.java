package interactions;

import block.Block;
import javafx.scene.text.Text;

public interface Interaction {
    default void act(){
        //default does nothing
    }

}
