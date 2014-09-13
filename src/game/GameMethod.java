package game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface GameMethod extends Remote{
	
	public HashMap<String,Object> move(int id,int dir) throws RemoteException;
	public HashMap<String,Object> ConnectToGame() throws RemoteException;
}
