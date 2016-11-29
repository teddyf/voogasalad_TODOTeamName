package block;

/**
 * Created by Bill Xiong on 11/20/16.
 * class for putting enemies on the board
 */
public class EnemyBlock extends CommunicatorBlock implements NotWalkable {
    private double health;
    private final double MAX_HEALTH = 100;
	public EnemyBlock(String name, int row, int col) {
        super(name, row, col);
        this.health = MAX_HEALTH;
    }
    public double getHealth(){
        return health;
    }
    public void setHealth(double val){
        health = val;
    }
}
