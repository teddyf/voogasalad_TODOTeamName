package block;

/**
 * Abstract block class which allows for the player to win by some sort of interaction.
 *
 * @author Filip Mazurek
 */
public abstract class WinBlock extends AbstractBlock {
    public WinBlock(String name,  int row, int col) {
        super(name, row, col);
    }
}
