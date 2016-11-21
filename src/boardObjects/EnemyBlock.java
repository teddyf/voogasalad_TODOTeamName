package boardObjects;

/**
 * Created by Bill Xiong on 11/20/16.
 * class for putting enemies on the board
 */
public class EnemyBlock extends CommunicatorBlock implements NotWalkable {
    private double health;

	public EnemyBlock(String name, int row, int col, double health) {
        super(name, row, col);
        this.health = health;
    }
    public double getHealth(){
        return health;
    }
    public void setHealth(double val){
        health = val;
    }
}
