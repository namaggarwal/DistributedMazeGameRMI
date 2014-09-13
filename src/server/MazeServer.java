package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.GameImplementation;



public class MazeServer{


	public static void main(String[] args) {
		
		if(args.length != 2){
			
			System.out.println("Usage java MazeServer <Size of grid> <Number of treasures>");
			System.exit(-1);
		}
		
		int gridsize = Integer.parseInt(args[0]);
		int nTreasures = Integer.parseInt(args[1]);
		
		if(nTreasures == 0){
			
			System.out.println("Number of tresures cannot be zero ");
			System.exit(-1);
		}
		
		Registry registry  = null;				
		
		MazeServer ms = new MazeServer();
		
		try {
			GameImplementation gs = new GameImplementation(gridsize,nTreasures);
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
