package ui.scenes.editor;

import block.BlockType;
import ui.scenes.editor.objects.GameObject2;
import ui.scenes.editor.objects.SingleBlockGameObject;

import java.io.File;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Harshil Garg on 12/7/16.
 */
public class ItemViewer {

    private final String root_path = "src/resources/images/tiles/";
    private final String image_root_path = "resources/images/tiles/";
    private final String extension = ".png";

    private GameObject2 selected = null;

    public List<GameObject2> getObjects(BlockType type) {
        String directory = root_path + type.name().toLowerCase();
        System.out.println(directory);
        File file = new File(directory);
        if (file == null)
            System.out.println("banana");

        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        String [] contents = file.list();
        if (contents == null)
            System.out.println("contents r null");
        System.out.println(java.util.Arrays.toString(contents));

        List<String> filteredPaths = new ArrayList<>();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i].contains(extension))
                contents[i] = contents[i].replace(extension, "");
            if (!contents[i].contains(".")) {
                filteredPaths.add(image_root_path + type.name().toLowerCase() + "/" + contents[i] + extension);
            }
        }
        System.out.println(filteredPaths);
        List<GameObject2> items = new ArrayList<>();

        for (int i = 0; i < filteredPaths.size(); i++) {
            String path = filteredPaths.get(i);
            GameObject2 item = new SingleBlockGameObject(path, type);
            items.add(item);
        }
        System.out.println(items);
        return items;
    }

    public void select(GameObject2 obj) {
        selected = obj;
    }

    public GameObject2 getSelected() {
        return selected;
    }

}
