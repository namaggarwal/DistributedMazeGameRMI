package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.GameImplementation;



public class MazeServer{


	public static void main(String[] args) {
		
		if(args.length != 1){
			System.err.println("Usage: java MazeServer <port number>");
			System.exit(1);
		}
		
						
		int port = Integer.parseInt(args[0]);
		
		
		Registry registry  = null;
		
		MazeServer ms = new MazeServer();
		
		try {
			GameImplementation gs = new GameImplementation();
			registry = LocateRegistry.getRegistry();
			registry.bind("GameImplementation", gs);
			System.out.println("Server Started");
			
		} catch (RemoteException re) {
			// TODO Auto-generated catch block
			System.err.println("Server Cannot be Started");
			re.printStackTrace();
		} catch(AlreadyBoundException abe){
			
			System.err.println("Already Bounded");
			abe.printStackTrace();
		}	    		
		 
		
	}  
	
}
