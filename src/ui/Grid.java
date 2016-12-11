//package ui;
//
//import javafx.scene.Group;
//
//import java.util.List;
//
///**
// * Created by Harshil Garg on 12/11/16.
// */
//public class Grid implements IGrid {
//
//    private int WRAP;
//
//    private int gridWidth;
//    private int gridHeight;
//    private int wrapWidth;
//    private int wrapHeight;
//
//    private Group group;
//
//    private List<GridPaneNode> blockList;
//    private List<GridPaneNode> clicked;
//    private GridPaneNode[][] grid;
//
//    public Grid() {
//        wrapWidth = gridWidth + WRAP/2;
//        wrapHeight = gridHeight + WRAP/2;
//    }
//
//    public void initializeGrid() {
//        int columns = wrapWidth;
//        int rows = wrapHeight;
//        gridMap = new GridObjectMap(columns, rows);
//        grid = new GridPaneNode[columns][rows];
//        for (int i = 0; i < columns; i++) {
//            for (int j = 0; j < rows; j++) {
//                GridPaneNode node = new GridPaneNode(i, j, defaultText());
//                blockList.add(node);
//                grid[j][i] = node;
//            }
//        }
//    }
//
//}
