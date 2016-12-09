package ui.scenes.editor.sidemenu;

import block.Block;
import block.BlockType;
import ui.scenes.editor.objects.GameObject;
import ui.scenes.editor.objects.MultipleBlockGameObject;
import ui.scenes.editor.objects.SingleBlockGameObject;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Harshil Garg on 12/7/16.
 */
public class ItemViewer {

    private final String image_root_path = "resources/images/tiles/";
    private final String root_path = "src/" + image_root_path;
    private final String extension = ".png";

    private GameObject selected = null;

 /*   public List<GameObject> getObjects(BlockType type) {
        String directory = root_path + type.name().toLowerCase();
        File file = new File(directory);
        String [] contents = file.list();
        Set<String> imagePrefixes = new HashSet<>();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i].contains(extension)) {
                contents[i] = contents[i].replace(extension, "");
                imagePrefixes.add(contents[i]);
            }
            if (!contents[i].contains(".")) {
                filteredPaths.add(image_root_path + type.name().toLowerCase() + "/" + contents[i] + extension);
            }
        }


        List<String> filteredPaths = new ArrayList<>();

        System.out.println(filteredPaths);
        List<GameObject> items = new ArrayList<>();

        for (int i = 0; i < filteredPaths.size(); i++) {
            String path = filteredPaths.get(i);
            GameObject item = new SingleBlockGameObject(path, type);
            items.add(item);
        }
        System.out.println(items);
        return items;
    }
*/
    public List<GameObject> getObjects(BlockType type) {
        String directory = root_path + type.name().toLowerCase();
        File file = new File(directory);
        String [] contents = file.list();

        List<GameObject> items = new ArrayList<>();


        Map<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < contents.length; i++) {
            String prefix = contents[i].substring(0, contents[i].indexOf('.'));
            if (map.containsKey(prefix)) {
                map.get(prefix).add(contents[i]);
            }
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(contents[i]);
                map.put(prefix, list);
            }
        }

        for (String key : map.keySet()) {
            ArrayList<String> value = map.get(key);
            if (value.size() == 1)
                items.add(buildSingleItem(key, type));
            else
                items.add(buildMultipleItem(key, value, type));
        }

        return items;

    }

    private GameObject buildSingleItem(String prefix, BlockType type) {
        String iconPath = image_root_path + type.name().toLowerCase() + "/" + prefix + extension;
        return new SingleBlockGameObject(iconPath, type);
    }

    private GameObject buildMultipleItem(String prefix, ArrayList<String> list, BlockType type) {
        int maxRow = 0;
        int maxCol = 0;
        for (String s : list) {
            int firstIndex = s.indexOf('.');
            int lastIndex = s.lastIndexOf('.');
            if (firstIndex == lastIndex) {
                continue;
            }
            String temp = s.substring(firstIndex + 1, lastIndex);
            String [] dimensions = temp.split("_");
            int row = Integer.parseInt(dimensions[0]);
            int col = Integer.parseInt(dimensions[1]);
            if (row > maxRow)
                maxRow = row;
            if (col > maxCol)
                maxCol = col;
        }

        String iconPath = image_root_path + type.name().toLowerCase() + "/" + prefix + extension;
        return new MultipleBlockGameObject(iconPath, type, maxRow, maxCol);

    }


    public void select(GameObject obj) {
        selected = obj;
    }

    public GameObject getSelected() {
        return selected;
    }

}
