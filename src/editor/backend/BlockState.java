package editor.backend;

public class BlockState {
    private int x, y;
    public BlockState(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
