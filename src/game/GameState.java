package game;

public class GameState {
	private static int gridSize;
	private static int treasures;
	private int treasuresRemaining;
	private int playersConnected;
	
	public static int getGridSize() {
		return gridSize;
	}
	public static void setGridSize(int gridSize) {
		GameState.gridSize = gridSize;
	}
	public static int getTreasures() {
		return treasures;
	}
	public static void setTreasures(int treasures) {
		GameState.treasures = treasures;
	}
	public int getTreasuresRemaining() {
		return treasuresRemaining;
	}
	public void setTreasuresRemaining(int treasuresRemaining) {
		this.treasuresRemaining = treasuresRemaining;
	}
	public int getPlayersConnected() {
		return playersConnected;
	}
	public void setPlayersConnected(int playersConnected) {
		this.playersConnected = playersConnected;
	}
	
}
