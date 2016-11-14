package editor.backend;

public interface Block {
	public int getRow();
	public int getCol();
	public BlockState getBlockState();
	public boolean hasPlayer();
}
