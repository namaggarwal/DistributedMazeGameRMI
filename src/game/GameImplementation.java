package game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

import server.WaitConnect;

public class GameImplementation extends UnicastRemoteObject implements GameMethod{

	private int boardSize;
	private int numberOfTreasures;
	private int[][] gameBoard;
	private int[][] playersLocation;
	private int remPlayers;
	int id = 0;
	
	GameInfo gameInfo = GameInfo.NotStarted;
	
	private static final long serialVersionUID = -4933868291603601249L;
	
	String[] msg = new String[2];

//	Randomly place treasures on the board.
	private void setTreasures(int numberOfTreasures){
		if(numberOfTreasures > 0){
			for(int i=0;i<numberOfTreasures;i++){
				int randomX = new Random().nextInt(boardSize);
				System.out.println(randomX);
				
				int randomY = new Random().nextInt(boardSize);
				System.out.println(randomY);
				//Treasures have values as non-zero integers on the board.
				gameBoard[randomX][randomY]=-1;
			}
		}	
	}
	
	private void printGameBoard(){
		System.out.flush();
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(this.gameBoard[i][j]+"\t");
			}
			System.out.println();
		}
		//Print Players 
	}
	
	private int score(Position playerPosition){
//		When player hits a treasure, he gains a point.
		
		playerPosition.getxPos();
		playerPosition.getyPos();
		return 0;
	}
	
	private void placePlayers(){
		//Gets all clients connected and places them on the board. Need a for loop
		Player player1 = new Player(1);
		Player player2 = new Player(2);

		Position position = new Position();
		player1.setPosition(position);
		player2.setPosition(position);
	
		this.gameBoard[position.getxPos()][position.getyPos()] = player1.getId();
		this.gameBoard[position.getxPos()][position.getyPos()] = player2.getId();
	}
	
	
	public GameImplementation(int bSize,int nTreasures) throws RemoteException {
		super();
//		Set variables- 
		this.boardSize = bSize;
		this.numberOfTreasures = nTreasures;
		this.gameBoard = new int[boardSize][boardSize];		
		remPlayers = bSize*bSize - 1;				
		playersLocation = new int[boardSize][boardSize];
//		Set Game Treasures
		setTreasures(this.numberOfTreasures);
//		Place treasures on the board
		placePlayers();
	
	}
	
	
	public HashMap saySomething(int id,String s){
		
		int otherid;
		
		
		if(gameInfo == GameInfo.Waiting){
			
			 String msg = "Game is in waiting state";
			 return createMessage(MessageType.Error, msg); 
			
		}
		
		msg[id] = s; 
		
		otherid = id==1?0:1;
		
		return createMessage(MessageType.MazeObject,"You Said "+msg[id]+" other said "+msg[otherid]);
		
		
	}
	
	public void startGame(){
		
		this.gameInfo = GameInfo.Started;
		
	}
	
	public int ConnectToGame(){
			
		switch(gameInfo){
		
			case NotStarted:
					gameInfo = GameInfo.Waiting;
					WaitConnect wc = new WaitConnect(this);
					wc.start();
					break;
			case Started:
					return -1;
			default:
				break;													
			
		}
								
			
		return id++; 
	}
	
	public HashMap createMessage(Integer msgType, Object msgObj){	
		
		HashMap <String,Object> hm = new HashMap<String,Object>();
		hm.put(Constants.MessageType, msgType);
		hm.put(Constants.MessageObject, msgObj);
				
		return hm;
		
		
	}
	
//	This function will modify the current position of the user
	public Position play(Player thisPlayer, char userInput){
/* 		TODO : Check whether a player hits a treasure, in which case he scores
			   Check whether a player hits another player, in which case he cannot move.
			   Check whether a player goes out of the board, in which case he cannot move.
*/
//		Get the current player's position
		Position currentPosition = thisPlayer.getPosition();
		Position newPosition = currentPosition;
//		Get his X and Y cordinates
		int xPos = newPosition.getxPos();
		int yPos = newPosition.getyPos();

		//Move according to client input
		switch (Character.toLowerCase(userInput)) {
		
			case 's':
				//MoveLeft - Xpos-- and YPos same 
				newPosition.setxPos(xPos--);
				break;
			
			case 'd':
				//MoveDown - YPos-- and Xpos same
				newPosition.setyPos(yPos--);
				break;
			
			case 'e':
				//MoveUp - YPos++ and XPos same
				newPosition.setyPos(yPos++);
				break;
			
			case 'f':
				//MoveRight - XPos++ and YPos same
				newPosition.setxPos(xPos++);
				break;
			
			default:
				System.out.println("Invalid Input!");
			}
		//Checking whether new position is out of the board -
		if(newPosition.getxPos() < 0 || 
				newPosition.getyPos()<0 || 
				newPosition.getxPos() > boardSize || 
				newPosition.getyPos() > boardSize)
			return newPosition;
		else 
			return newPosition;
		
	}
	
	
			
}
