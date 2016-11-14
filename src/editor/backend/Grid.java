package editor.backend;

public interface Grid {
	public boolean isInBounds();
	public int getWidth();
	public int getHeight();
	public Grid getGrid();
}
