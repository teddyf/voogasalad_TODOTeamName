package boardObjects;

/**
 * Created by Bill Xiong on 11/20/16.
 * class for putting enemies on the board
 */
public class EnemyBlock extends CommunicatorBlock implements NotWalkable {
    
	public EnemyBlock(String name, int row, int col) {
        super(name, row, col);
    }
	
}
