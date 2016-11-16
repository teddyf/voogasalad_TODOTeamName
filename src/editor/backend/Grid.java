package editor.backend;

public interface Grid {
	public boolean isInBounds();
	public int getNumRows();
	public int getNumCols();
	public Grid getGrid();
}
