package block;

/**
 * Class for putting enemies on the board
 * 
 * @author Daniel Chai, Bill Xiong
 */
public class EnemyBlock extends CommunicatorBlock {
	private static final int DEFAULT_HEALTH = 100;
	
	private int health;

	public EnemyBlock(String name, int row, int col) {
		super(name, row, col);
		this.health = DEFAULT_HEALTH;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int val) {
		health = val;
	}
}
