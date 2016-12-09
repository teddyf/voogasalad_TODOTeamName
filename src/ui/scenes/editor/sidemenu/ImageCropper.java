package ui.scenes.editor.sidemenu;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by harshilgarg on 12/9/16.
 */
public class ImageCropper {

    private String myOriginalPath;
    private int myRowNumber;
    private int myColumnNumber;

    public ImageCropper(String originalPath, int r, int c) {
        myOriginalPath = originalPath;
        myRowNumber = r;
        myColumnNumber = c;
        crop();
    }

    private void crop() {
        try {
            System.out.println(myOriginalPath);
            Image image = ImageIO.read(new File(myOriginalPath));
            int x = 10, y = 20, w = 40, h = 50;
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(image, 0, 0, w, h, x, y, x + w, y + h, null);
            String myNewPath = myOriginalPath.replace(".png", "").concat("hi").concat(".png");
            ImageIO.write(dst, "png", new File(myNewPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

