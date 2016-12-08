package block;

/**
 * Created by Harshil Garg on 12/8/16.
 */
public class GroundBlock extends Block {

    public GroundBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }

}
