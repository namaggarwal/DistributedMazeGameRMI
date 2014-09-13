package server;

import game.GameImplementation;

public class WaitConnect extends Thread {
	
	GameImplementation gi;
	
	public WaitConnect(GameImplementation gi){
		
		this.gi = gi;
		
	}
	
	
	public void run(){
		
		System.out.println("Waiting for 20 seconds before starting the game !!!!");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.gi.setRandomTreasures();		
		this.gi.startGame();
		System.out.println("Game Started");
		
	}
	
	
	
}
