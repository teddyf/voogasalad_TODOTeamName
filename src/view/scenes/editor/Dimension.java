package view.scenes.editor;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class stores width and height information for 2D dimensions.
 */
public class Dimension {

    private final int width;
    private final int height;

    Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width
     *
     * @return the width
     */
    public int width() {
        return width;
    }

    /**
     * Gets the height
     *
     * @return the height
     */
    public int height() {
        return height;
    }
}