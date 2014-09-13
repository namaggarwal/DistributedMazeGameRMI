package client;

import game.Constants;
import game.Direction;
import game.GameMethod;
import game.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class MazeClient {

	static int clientID;
	
    private MazeClient() {}

    public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		GameMethod gs = null;
		int msgType;
		HashMap<String,Object> res = null;
		
		try {
		    Registry registry = LocateRegistry.getRegistry(host);
		    gs = (GameMethod) registry.lookup("GameImplementation");
		    
		    
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
		
		
		try{			
			
			res = gs.ConnectToGame();			
			
		}catch(RemoteException re){
			
			System.out.println("Seems like server is not running or is throwing some exception.");
			re.printStackTrace();
		}
		
		msgType = Integer.parseInt(res.get(Constants.MessageType).toString());
		
		switch(msgType){
		
			case MessageType.ConnectSuccess:
				clientID = Integer.parseInt(res.get(Constants.MessageObject).toString());
				System.out.println("Connected with id "+ clientID);
				break;
			case MessageType.ConnectError:
				String msg =res.get(Constants.MessageObject).toString(); 
				System.out.println(msg);
				System.exit(-1);
				break;
			default:
				System.out.println("Unknown message type!!!!");
				System.exit(-1);										
		
		}
				 	 	    
	    
	    
	    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String text;			
    	
    	try{
    		
    		while(true){
    			
    			text = br.readLine();        	
    			switch(text.toUpperCase().charAt(0)){
    				
    			case 'W': res = gs.move(clientID,Direction.UP);
    				break;
    			case 'A': res = gs.move(clientID,Direction.LEFT);
					break;
    			case 'S': res = gs.move(clientID,Direction.DOWN);
					break;
    			case 'D': res = gs.move(clientID,Direction.RIGHT);
    				break;
    			case 'E': res = gs.move(clientID,Direction.STAY);
					break;
				default:
					System.out.println("Invalid Move!!!");
    			
    			}
    			
    			
    			msgType = Integer.parseInt(res.get(Constants.MessageType).toString());
        		String message = null;
        		
        		switch(msgType){
        			
        			case MessageType.Error:
        							// Get the error message and print it
        							message = res.get(Constants.MessageObject).toString();
        							System.out.println(message);
        							break;
        			case MessageType.MazeObject:
    			    				message = res.get(Constants.MessageObject).toString();
    								System.out.println(message);									
        							break;
        			default :
        					System.out.println("Unknown response from the server");
        			
        		
        		}
        		
    			
    			
    		}
    			
    				    		
    	}catch(RemoteException re){
			
			
		} catch (IOException e) {
			System.out.println("Cannot read from standard input");
			e.printStackTrace();
		}
    	
	    
    
    	
	
    }
}
