package grid;
import java.util.*;

public class GridPane {
    //name & identifier & row/column
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private Map<Double, Map<Double, GridPaneNode>> renderMap;
    
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
        this.renderMap = new HashMap<Double,Map<Double,GridPaneNode>>();
        setRenderMap();
        
    }
    
    private double getXRender(GridPaneNode node){
        double cellWidth = 0.0+renderWidth/gridWidth;
        int sol = node.getCol();
        return sol*cellWidth;
    }
    
    private double getYRender(GridPaneNode node){
        double cellHeight = 0.0+renderHeight/gridHeight;
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
            double x = getXRender(node);
            double y = getYRender(node);
            node.setImageSize(0.0+renderWidth/gridWidth,0.0+renderHeight/gridHeight);
            node.setImageCoord(x,y);
            if(renderMap.containsKey(x)){
                renderMap.get(x).put(y, node);
            }
            else{
                renderMap.put(x, new HashMap<Double,GridPaneNode>());
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
    
    public Map<Double,Map<Double,GridPaneNode>> getRenderMap(){
        return renderMap;
    }
    
    
}
