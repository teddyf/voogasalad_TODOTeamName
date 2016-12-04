package block;

/**
 * Created by anindamanocha on 12/3/16.
 */
public class TeleportReceiveBlock extends Block {
    public TeleportReceiveBlock(String name, int row, int col) {
        super(name, row, col);
        setWalkableStatus(true);
    }
}
