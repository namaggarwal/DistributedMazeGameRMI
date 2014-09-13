package game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface GameMethod extends Remote{

	
	public HashMap saySomething(int id,String s) throws RemoteException;
	public int ConnectToGame() throws RemoteException;
}
