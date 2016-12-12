package ui.scenes.editor.sidemenu;

import block.BlockType;
import ui.scenes.editor.objects.GameObject;
import ui.scenes.editor.objects.MultipleBlockGameObject;
import ui.scenes.editor.objects.SingleBlockGameObject;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Harshil Garg
 *         <p>
 *         Used for displaying overworld items.
 */
public class ItemViewer {

    private final String image_root_path = "resources/images/tiles/";
    private final String root_path = "src/" + image_root_path;
    private final String extension = ".png";

    private GameObject selected = null;


    List<GameObject> getObjects(BlockType type) {
        String directory = root_path + type.name().toLowerCase();
        File file = new File(directory);
        String[] contents = file.list();
        List<GameObject> items = new ArrayList<>();
        Map<String, ArrayList<String>> map = new TreeMap<>();
        for (String content : contents) {
            String prefix = content.substring(0, content.indexOf('.'));
            if (map.containsKey(prefix)) {
                map.get(prefix).add(content);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(content);
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
        return new SingleBlockGameObject(getFullURIFileName(prefix, type), type);
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
            String[] dimensions = temp.split("_");
            int row = Integer.parseInt(dimensions[0]);
            int col = Integer.parseInt(dimensions[1]);
            if (row > maxRow)
                maxRow = row;
            if (col > maxCol)
                maxCol = col;
        }

        return new MultipleBlockGameObject(getFullURIFileName(prefix, type), type, maxRow, maxCol);

    }

    private String getFullURIFileName(String prefix, BlockType type) {
        File file = new File(root_path + type.name().toLowerCase() + "/" + prefix + extension);
        return file.toURI().toString();
    }

    void select(GameObject obj) {
        selected = obj;
    }

    GameObject getSelected() {
        return selected;
    }
}
