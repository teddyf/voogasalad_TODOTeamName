package boardObjects;

/**
 * A shallow block interface. This interface provides access to only the basic information within a block, and does not
 * allow any modification access.
 *
 * @author Filip Mazurek
 */
public interface ShallowBlock {

    int getRow();

    int getCol();

    String getName();
}
