package client;

import game.GameMethod;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

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
			System.out.println("Connected with id "+clientID);
		}catch(RemoteException re){
			
			
		}
	    
	    if(clientID == -1){
	    	
	    	System.out.println("Cannot connect to game!!! It started already.");
	    	System.exit(-1);
	    	
	    }
	    
	    while(true){
	    	
	    	Scanner s=new Scanner(System.in);
	    	String text = s.nextLine().trim();
	    	try{ 	
	    		String res = gs.saySomething(clientID, text);
	    		System.out.println(res);
	    	}catch(RemoteException re){
				
				
			}
	    	
	    }
    
    	
	
    }
}
