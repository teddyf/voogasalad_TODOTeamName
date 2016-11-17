package grid;
import java.util.*;

public class GridPane {
    //name & identifier & row/column
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private Map<Integer, Map<Integer, GridPaneNode>> renderMap;
    
    private int gridWidth;
    private int gridHeight;
    private int renderWidth;
    private int renderHeight;
    
    
    private String DEFAULT = "DEFAULT.1.2.3";
    
    public GridPane(int gridWidth, int gridHeight, int renderWidth, int renderHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        initializeGrid();
        this.renderMap = new HashMap<Integer,Map<Integer,GridPaneNode>>();
        setRenderMap();
        
    }
    
    private int getXRender(GridPaneNode node){
        int cellWidth = renderWidth/gridWidth;
        int sol = node.getCol();
        return sol*cellWidth;
    }
    
    private int getYRender(GridPaneNode node){
        int cellHeight = renderHeight/gridHeight;
        int sol = node.getRow();
        return sol*cellHeight;
    }
    
    private void initializeGrid(){
        for(int i = 0; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){
                GridPaneNode node = new GridPaneNode(i,j,DEFAULT);
                blockList.add(node);
            }
        }
    }
    
    public void setRenderMap(){
        for(int i = 0; i < blockList.size(); i++){
            GridPaneNode node = blockList.get(i);
            int x = getXRender(node);
            int y = getYRender(node);
            if(renderMap.containsKey(x)){
                renderMap.get(x).put(y, node);
            }
            else{
                renderMap.put(x, new HashMap<Integer,GridPaneNode>());
                renderMap.get(x).put(y, node);
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
    
    public List<GridPaneNode> getNodeList(){
        return blockList;
    }
    
    public void setNodes(List<GridPaneNode> list){
        this.blockList = list;
    }
    
    

    
}
