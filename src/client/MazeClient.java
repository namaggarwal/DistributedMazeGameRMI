package client;

import game.GameMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	    		System.out.println("Naman Aggarwal");
	    		Object res = gs.saySomething(clientID, text);
	    		System.out.println(res.toString());
	    	}catch(RemoteException re){
				
				
			} catch (IOException e) {
				System.out.println("Cannot read from standard input");
				e.printStackTrace();
			}
	    	
	    }
    
    	
	
    }
}
