package grid;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;

/**
 * 
 * @author Teddy Franceschi
 *
 */
public class GridPane {
    //name & identifier & row/column
    
    private Group group;
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private Map<Double, Map<Double, GridPaneNode>> renderMap;
    
    private double gridWidth;
    private double gridHeight;
    private double renderWidth;
    private double renderHeight;
    private int renderTopLeftX;
    private int renderTopLeftY;
    private ColorAdjust hoverOpacity;
    
    private String DEFAULT = "DEFAULT.1.2.3";
     
    public GridPane(int gridWidth, int gridHeight, int renderWidth, int renderHeight, int renderTopLeftX, int renderTopLeftY){
        this.group = new Group();
        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);
        this.renderTopLeftX = renderTopLeftX;
        this.renderTopLeftY = renderTopLeftY;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        this.renderMap = new HashMap<Double,Map<Double,GridPaneNode>>();
        initializeGrid();
        setRenderMap();
    }
    
    
    private double getXRender(GridPaneNode node){
        double cellWidth = 0.0+renderWidth/gridWidth;
        int sol = node.getCol();
        return sol*cellWidth + renderTopLeftX;
    }
    
    private double getYRender(GridPaneNode node){
        double cellHeight = 0.0+renderHeight/gridHeight;
        int sol = node.getRow();
        return sol*cellHeight + renderTopLeftY;
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
            node.getImage().setOnMouseClicked(e->{
                click(node);
                node.displayOptions();
                System.out.println(getClicked());
            });
            node.getImage().setOnMouseEntered(e -> node.getImage().setEffect(hoverOpacity));
            node.getImage().setOnMouseExited(e -> node.getImage().setEffect(null));
            
            group.getChildren().add(node.getImage());
            if(renderMap.containsKey(x)){
                renderMap.get(x).put(y, node);
            }
            else{
                renderMap.put(x, new HashMap<>());
                renderMap.get(x).put(y, node);
            }
        }
    }
    
    public void resizeReset(double x, double y){
        this.gridWidth = x;
        this.gridHeight = y;
        reset();
        
    }
    
    public void resetKeepSize(){
        reset();
    }
    
    private void click(GridPaneNode node){
        if(clicked.contains(node)){
            clicked.remove(node);
        }
        else{
            clicked.add(node);
        }
    }
    
    private void reset(){
        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        this.renderMap = new HashMap<Double,Map<Double,GridPaneNode>>();
        initializeGrid();
        setRenderMap();
    }
    
    public void swap(List<GridPaneNode> list){
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < clicked.size(); j++){
                clicked.get(j).swap(list.get(i),list.get(i).getImageNum());
            }
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
    
    public Group getGroup(){
        return group;
    }
    
    public List<GridPaneNode> getClicked(){
        return clicked;
    }
    
    
}
