package game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.WaitConnect;

public class GameImplementation extends UnicastRemoteObject implements GameMethod{

	GameState gameState = GameState.NotStarted;
	
	int id = 0;
	
	private static final long serialVersionUID = -4933868291603601249L;
	
	String[] msg = new String[2];

	
	public GameImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String saySomething(int id,String s){
		
		int otherid;
		
		
		if(gameState == GameState.Waiting){
			
			return "Game is in waiting state";
			
		}
		
		msg[id] = s; 
		
		otherid = id==1?0:1;
	
		return "You Said "+msg[id]+" other said "+msg[otherid];
		
		
	}
	
	public void startGame(){
		
		this.gameState = GameState.Started;
		
	}
	
	public int ConnectToGame(){
		
		
		switch(gameState){
		
			case NotStarted:
					gameState = GameState.Waiting;
					WaitConnect wc = new WaitConnect(this);
					wc.start();
					break;
			case Started:
					return -1;													
		
		}
								
			
		return id++; 
	}
		
			
}
