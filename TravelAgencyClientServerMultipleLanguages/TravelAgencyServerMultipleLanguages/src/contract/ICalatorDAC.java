package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Calator;

public interface ICalatorDAC extends Remote {

	public List<String> getSellTravellers() throws RemoteException;

	public Calator getTravellerByName(String name) throws RemoteException;
	
	public void addSoldTicket(int persID, int nrBilet) throws RemoteException;
}
