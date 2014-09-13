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
	private HashMap <Integer,Player> pList;
	private int maxPlayers;
	int lastId = 0;
	
	GameInfo gameInfo = GameInfo.NotStarted;
	
	private static final long serialVersionUID = -4933868291603601249L;
	
	String[] msg = new String[2];
	
	
	public GameImplementation(int bSize,int nTreasures) throws RemoteException {
		super();
 
		this.boardSize = bSize;
		this.numberOfTreasures = nTreasures;
		this.gameBoard = new int[boardSize][boardSize];	
		this.pList = new HashMap<Integer,Player>();
		//Initialize GameBoard with all zeros
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
				this.gameBoard[i][j] = 0;
			}
		}
		
		printGameBoard();
				
		this.maxPlayers = bSize*bSize - 1;
		//setTreasures(this.numberOfTreasures);	
	
	}
	
	

//	Randomly place treasures on the board.
	private void setTreasures(int numberOfTreasures){
		if(numberOfTreasures > 0){
			for(int i=0;i<numberOfTreasures;i++){
				int randomX = new Random().nextInt(boardSize);

				int randomY = new Random().nextInt(boardSize);

				//Treasures have values as non-zero integers on the board.
				gameBoard[randomX][randomY]=-1;
			}
		}	
	}
	
	private void printGameBoard(){
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(this.gameBoard[i][j]+"\t");
			}
			System.out.println();
		}
	
	}
	
	
	private Boolean isOccupiedByPlayer(int x,int y){
		
		if(this.gameBoard[x][y] > 0){
			return true;
		}else{
			
			return false;
		}
		
	}
	
	private void setRandomPlayerPosition(Player p){
		
		int x = 0;
		int y = 0;
		
		Random rm;
				
		while(true){
			
			rm = new Random();
			x = rm.nextInt(this.boardSize);
			y = rm.nextInt(this.boardSize);
			
			if(!this.isOccupiedByPlayer(x, y)){
				
				p.setxPos(x);
				p.setyPos(y);				
				this.gameBoard[x][y] = p.getId();				
				break;
				
			}
			
		}
		
		
	}

	
	public HashMap<String,Object> saySomething(int id,String s){
		
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
	
	public HashMap<String,Object> ConnectToGame(){
		
		 
		switch(gameInfo){
		
			case NotStarted:
					gameInfo = GameInfo.Waiting;
					WaitConnect wc = new WaitConnect(this);
					wc.start();
					break;
			case Started:
					String msg = "Game has already started !!! Cannot join now.";
					return createMessage(MessageType.ConnectError,msg);
			default:
				break;													
			
		}
		
		this.lastId++;
		
		if(this.lastId<=this.maxPlayers){
			
			Player p = new Player(this.lastId);
			this.pList.put(this.lastId, p);
			setRandomPlayerPosition(p);
			this.printGameBoard();
			return createMessage(MessageType.ConnectSuccess,this.lastId);
			
		}else{
			
			return createMessage(MessageType.ConnectError,"Maximum players limit reached. Cannot connect now");
		}
					
		
	}
	
	public HashMap<String,Object> createMessage(Integer msgType, Object msgObj){	
		
		HashMap <String,Object> hm = new HashMap<String,Object>();
		hm.put(Constants.MessageType, msgType);
		hm.put(Constants.MessageObject, msgObj);
				
		return hm;
		
		
	}
	
	/*
//	This function will modify the current position of the user
	public Position play(Player thisPlayer, char userInput){
 		TODO : Check whether a player hits a treasure, in which case he scores
			   Check whether a player hits another player, in which case he cannot move.
			   Check whether a player goes out of the board, in which case he cannot move.
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
	
	*/
			
}
