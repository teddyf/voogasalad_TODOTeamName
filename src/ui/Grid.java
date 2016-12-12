//package ui;
//
///**
// * @author Harshil Garg
// */
//public class Grid {
//
//    public Grid(int gridWidth, int gridHeight, int renderWidth,
//                int gridHeight, int renderTopLeftX, int renderTopLeftY) {
//
//    }
//
//    private void initializeGrid() {
//        int columns = (int) gridWidth + WRAP;
//        int rows = (int) gridHeight + WRAP;
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
