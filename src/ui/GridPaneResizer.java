package ui;

import java.util.*;
import grid.GridGrowthDirection;

public class GridPaneResizer {
    
    private GridPane gridPane;
    private int amount;
    private GridGrowthDirection dir;
    private GridObjectMap objMap;
    
    private String DEFAULT = "resources/images/tiles/ground/grass-";
    
    public GridPaneResizer(){

    }
    
    
    public void resize(GridGrowthDirection dir, int amount, GridObjectMap objMap, GridPane gridPane){
        
        if(dir == GridGrowthDirection.RIGHT){
            if(amount>=0){
                List<GridPaneNode> blockList = addHorizontalBlocks(amount);
                gridPane.setGridHeight(gridPane.getHeight()+amount);
                gridPane.setGridWidth(gridPane.getWidth()+amount);
                setGridArray(blockList);
            }
        }
        else if(dir == GridGrowthDirection.BOTTOM){
            if(amount>=0){
                List<GridPaneNode> blockList = addVerticalBlocks(amount);
                gridPane.setGridHeight(gridPane.getHeight()+amount);
                gridPane.setGridWidth(gridPane.getWidth()+amount);
                setGridArray(blockList);
            }
        }
        else if(dir == GridGrowthDirection.TOP){
            if(amount>=0){
                List<GridPaneNode> blockList = addVerticalBlocks(amount);
                shiftTopAdd(amount, blockList);
                gridPane.setGridHeight(gridPane.getHeight()+amount);
                gridPane.setGridWidth(gridPane.getWidth()+amount);
                setGridArray(blockList);
            }
        }
        else if(dir == GridGrowthDirection.LEFT){
            if(amount >= 0){
                List<GridPaneNode> blockList = addHorizontalBlocks(amount);
                shiftLeftAdd(amount, blockList);
                gridPane.setGridHeight(gridPane.getHeight()+amount);
                gridPane.setGridWidth(gridPane.getWidth()+amount);
                setGridArray(blockList);
            }
        }
    }
    
    public void resizer(){
        
    }
    
    private List<GridPaneNode> addHorizontalBlocks(int amount){
        List<GridPaneNode> blockList = gridPane.getNodeList();        
        for(int i = 0; i < gridPane.getHeight(); i++){
            for(int j = 0; j < amount; j++){
                GridPaneNode temp = new GridPaneNode(i,amount+j, defaultText());
                blockList.add(temp);
            }    
        }
        return blockList;
    }
    
    private List<GridPaneNode> addVerticalBlocks(int amount){
        List<GridPaneNode> blockList = gridPane.getNodeList();  
        for(int i = 0; i < gridPane.getWidth(); i++){
            for(int j = (int)gridPane.getHeight(); j < gridPane.getHeight()+amount; j++){
                GridPaneNode temp = new GridPaneNode(j,i, defaultText());
                blockList.add(temp);
            }    
        }
        return blockList;
    }
    
    private void setGridArray(List<GridPaneNode> list){
        GridPaneNode[][] updatedGrid = new GridPaneNode[(int)gridPane.getWidth()][(int)gridPane.getHeight()];
        for(int i = 0; i < list.size(); i++){
            int tempCol = list.get(i).getCol();
            int tempRow = list.get(i).getRow();
            updatedGrid[tempCol][tempRow] = list.get(i);
        }
        gridPane.setGridArray(updatedGrid);
        gridPane.setNodes(list);
    }
    private void shiftLeftAdd(int amount, List<GridPaneNode> list){
        for(int i = 0; i < list.size(); i++){
            GridPaneNode node = list.get(i);
            int currCol = node.getCol();
            int currRow = node.getRow();
            if(currCol < gridPane.getWidth()){
                node.setCol(currCol++);
            }
            if(currCol >= gridPane.getWidth()){
                node.setCol((int)(node.getCol()-gridPane.getWidth()));
            }
            
        }
    }
    
    private void shiftTopAdd(int amount, List<GridPaneNode> list){
        for(int i = 0; i < list.size(); i++){
            GridPaneNode node = list.get(i);
            int currCol = node.getCol();
            int currRow = node.getRow();
            if(currRow < gridPane.getHeight()){
                node.setRow(currRow++);
            }
            if(currRow >= gridPane.getHeight()){
                node.setRow((int)(node.getRow()-gridPane.getHeight()));
            }
            
        }
    }

    private void reduceBlocks(){
        
    }
    
    private String defaultText () {
        int suffix = randomNumber(1, 4);
        return DEFAULT + suffix + ".png";
    }
    
    private int randomNumber (int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
