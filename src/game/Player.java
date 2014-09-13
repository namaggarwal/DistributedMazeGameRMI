package game;

public class Player {
	private int playerScore;
	private Position position;
	private int id;
	
	//Initializing the player - 
	public void Player(int id, Position initialPosition){
		this.id = id;
		this.position = initialPosition;
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

	public void setId(int id) {
		this.id = id;
	}

	public int score(){
		this.playerScore = this.playerScore++;
		return this.playerScore;
	}

}
