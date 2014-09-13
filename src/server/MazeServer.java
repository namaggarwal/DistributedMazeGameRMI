package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.GameImplementation;



public class MazeServer{


	public static void main(String[] args) {
										
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
