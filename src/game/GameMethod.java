package game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameMethod extends Remote{

	
	public String saySomething(int id,String s) throws RemoteException;
	public int ConnectToGame() throws RemoteException;
}
