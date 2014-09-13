package game;

public class Player {
	private int playerScore;
	private Position position;
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public int score(){
		this.playerScore = this.playerScore++;
		return this.playerScore;
	}

}
