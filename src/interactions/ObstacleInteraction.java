package interactions;

import boardObjects.Block;
import boardObjects.ObstacleBlock;
import player.Player;

/**
 * defines interactions for obstacles
 * @author Bill Xiong
 */
public class ObstacleInteraction extends AbstractInteraction{
    private ObstacleBlock obstacle;
    public ObstacleInteraction(ObstacleBlock o){
        obstacle = o;
    }
    public void act(){
        //do nothing
    }
}
