package game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import server.WaitConnect;

public class GameImplementation extends UnicastRemoteObject implements GameMethod{

	GameInfo gameInfo = GameInfo.NotStarted;
	
	int id = 0;
	
	private static final long serialVersionUID = -4933868291603601249L;
	
	String[] msg = new String[2];

	
	public GameImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Object saySomething(int id,String s){
		
		int otherid;
		
		
		if(gameInfo == GameInfo.Waiting){
			
			 String msg = "Game is in waiting state";
			 return createMessage(OutputType.Error, msg); 
			
		}
		
		msg[id] = s; 
		
		otherid = id==1?0:1;
		
		return "You Said "+msg[id]+" other said "+msg[otherid];
		
		
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
		
		
		HashMap <Integer,Object> hm = new HashMap<Integer,Object>();
				
		return hm;
		
		
	}
		
			
}
