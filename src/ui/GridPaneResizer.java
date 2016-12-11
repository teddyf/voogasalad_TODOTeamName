package ui;

import java.util.*;
import grid.GridGrowthDirection;

public class GridPaneResizer {
    
    private GridPane gridPane;
    private int amount;
    private GridGrowthDirection dir;
    
    private String DEFAULT = "resources/images/tiles/ground/grass-";
    
    public GridPaneResizer(GridPane gridPane, int amount, GridGrowthDirection dir){
        this.gridPane = gridPane;
        this.amount = amount;
        this.dir = dir;
    }
    
    public void resize(GridGrowthDirection dir){
        if(dir == GridGrowthDirection.RIGHT){
            
        }
        else if(dir == GridGrowthDirection.BOTTOM){
            
        }
        else if(dir == GridGrowthDirection.TOP){
            
        }
        else if(dir == GridGrowthDirection.LEFT){
            
        }
    }
    
    public void resizer(){
        
    }
    
    private void addBlocks(int amount){
        List<GridPaneNode> blockList = gridPane.getNodeList();
        
        for(int i = 0; i < gridPane.getHeight(); i++){
            for(int j = 0; j < amount; j++){
                GridPaneNode temp = new GridPaneNode(i,amount+j, defaultText());
                blockList.add(temp);
            }    
        }
        gridPane.setNodes(blockList);
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
    private void setGridArray(List<GridPaneNode> list, int row, int col){
        GridPaneNode[][] updatedGrid = new GridPaneNode[col][row];
        for(int i = 0; i < list.size(); i++){
            int tempCol = list.get(i).getCol();
            int tempRow = list.get(i).getRow();
            updatedGrid[tempCol][tempRow] = list.get(i);
        }
        gridPane.setGridArray(updatedGrid);
    }
    
    private void shiftAdd(int amount, List<GridPaneNode> list, GridGrowthDirection dir){
        for(int i = 0; i < list.size()-amount; i++){
            GridPaneNode temp = list.get(i);
            if(dir.equals(GridGrowthDirection.TOP)){
                temp.setRow(temp.getRow()+amount);
            }
            if(dir.equals(GridGrowthDirection.LEFT)){
                temp.setCol(temp.getCol()+amount);
            }
        }
        for(int i = list.size()-amount; i < list.size(); i++){
            GridPaneNode temp = list.get(i);
            
        }
    }
}
