package interactions;

import javafx.scene.text.Text;

public interface Interaction {
    default void act(){
        //default does nothing
    }
}
