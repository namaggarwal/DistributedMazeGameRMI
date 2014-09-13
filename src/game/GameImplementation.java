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
	int id = 0;
	
	GameInfo gameInfo = GameInfo.NotStarted;
	
	private static final long serialVersionUID = -4933868291603601249L;
	
	String[] msg = new String[2];

	//Randomly place treasures on the board.
	private void placeTreasures(int numberOfTreasures){
		int randomX = new Random().nextInt(numberOfTreasures);
		int randomY = new Random().nextInt(numberOfTreasures);
			
		for(int i=0;i<numberOfTreasures;i++){
			//Treasures have values as non-zero integers on the board.
			gameBoard[randomX][randomY] = i;
		}
	}
	
	private void prepareGameBoard(){
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if(gameBoard[i][j]==0)
					System.out.println("_");
				else
					System.out.println("gameBoard[i][j]");
			}
		}
	}

	
	public GameImplementation() throws RemoteException {
		super();
		//Set variables- 
				this.boardSize = boardSize;
				this.numberOfTreasures = numberOfTreasures;
				gameBoard = new int[boardSize][boardSize];
				playersLocation = new int[boardSize][boardSize];
				//Place treasures on the board
				//placeTreasures();
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
		//Get the current player's position
		Position newPosition = thisPlayer.getPosition();
		
		//Get his X and Y cordinates
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
		
		return newPosition;
		
	}
	
	
			
}
