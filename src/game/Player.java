package game;

public class Player {
	
	private int playerScore;
	private int xPos;
	private int yPos;
	private int id;
	 
	public Player(int id){
		this.id = id;		
		this.playerScore = 0;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	
	public void addPlayerScore(int addScore) {
		this.playerScore += addScore;
	}

	
	public int getId() {
		return id;
	}

	public int getScore(){
		
		return this.playerScore;
	}
	
	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


}
