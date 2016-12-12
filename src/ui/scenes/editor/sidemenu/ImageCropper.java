package ui.scenes.editor.sidemenu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Harshil Garg
 *         <p>
 *         Crops images for adding new user-created game objects.
 */
class ImageCropper {

    private String myOriginalPath;
    private int myRowNumber;
    private int myColumnNumber;

    public ImageCropper(String originalPath, int row, int column) {
        myOriginalPath = originalPath;
        myRowNumber = row;
        myColumnNumber = column;
        crop();
    }

    private void crop() {
        try {
            BufferedImage image = ImageIO.read(new File(myOriginalPath));

            int width = image.getWidth();
            int height = image.getHeight();
            int cropWidth = width / myColumnNumber;
            int cropHeight = height / myRowNumber;

            BufferedImage[][] dst = new BufferedImage[myRowNumber][myColumnNumber];
            for (int i = 0; i < myRowNumber; i++) {
                for (int j = 0; j < dst[0].length; j++) {
                    dst[i][j] = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_ARGB);
                    dst[i][j].getGraphics().drawImage(image, 0, 0, cropWidth, cropHeight, j * cropWidth, i * cropHeight,
                            (j + 1) * cropWidth, (i + 1) * cropHeight, null);

                    String rowString = String.valueOf(i + 1);
                    String columnString = String.valueOf(j + 1);
                    String myNewPath = myOriginalPath.replace(".png", "").concat(".").concat(rowString).
                            concat("_").concat(columnString).concat(".png");
                    try {
                        ImageIO.write(dst[i][j], "png", new File(myNewPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

