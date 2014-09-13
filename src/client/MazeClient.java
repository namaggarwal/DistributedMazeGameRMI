package client;

import game.Constants;
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
		
		try {
		    Registry registry = LocateRegistry.getRegistry(host);
		    gs = (GameMethod) registry.lookup("GameImplementation");
		    
		    
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
		
		
		try{
			
			clientID = gs.ConnectToGame();
			
		}catch(RemoteException re){
			
			
		}
	    
	    if(clientID == -1){
	    	
	    	System.out.println("Cannot connect to game!!! It started already.");
	    	System.exit(-1);
	    	
	    }
	    
	    System.out.println("Connected with id "+clientID);
	    
	    while(true){
	    	
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	String text;			
	    	
	    	try{
	    		text = br.readLine();	    		
	    		HashMap res = gs.saySomething(clientID, text);
	    		Integer msgType = Integer.parseInt(res.get(Constants.MessageType).toString());
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
	    			    		
	    	}catch(RemoteException re){
				
				
			} catch (IOException e) {
				System.out.println("Cannot read from standard input");
				e.printStackTrace();
			}
	    	
	    }
    
    	
	
    }
}
