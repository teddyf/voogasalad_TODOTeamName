package block;

/**
 * Abstract block class which allows for the player to win by some sort of interaction.
 *
 * @author Filip Mazurek, Aninda Manocha
 */
public abstract class WinBlock extends Block {
    public WinBlock(String name,  int row, int col) {
        super(name, row, col);
    }


}
