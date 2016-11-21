package boardObjects;

import editor.backend.Interaction;

import java.util.List;

/**
 * Created by Bill Xiong on 11/20/16.
 * class for putting enemies on the board
 */
public class Enemy extends CommunicatorBlock implements NotWalkable{
    public Enemy(String name) {
        super(name);
    }
}
