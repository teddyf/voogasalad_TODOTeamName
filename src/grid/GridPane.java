package grid;
import java.util.*;

public class GridPane {
    //name & identifier & row/column
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private int gridWidth;
    private int gridHeight;
    private int renderWidth;
    private int renderHeight;
    
    private String DEFAULT = "DEFAULT";
    
    public GridPane(int gridWidth, int gridHeight, int renderWidth, int renderHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        initializeGrid();
        
    }
    
    private void initializeGrid(){
        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){
                GridPaneNode node = new GridPaneNode(i,j,DEFAULT);
                blockList.add(node);
            }
        }
    }
    
    public void click(GridPaneNode node){
        if(clicked.contains(node)){
            clicked.remove(node);
        }
        else{
            clicked.add(node);
        }
    }
    
}
