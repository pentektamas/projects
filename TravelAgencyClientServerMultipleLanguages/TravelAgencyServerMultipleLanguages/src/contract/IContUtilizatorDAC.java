package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IContUtilizatorDAC extends Remote {

	public int getLastContID() throws RemoteException;

	public boolean isDuplicateAccountInput(String user) throws RemoteException;

	public void addNewAccount(String user, String password) throws RemoteException;

	public boolean isDuplicateUsername(int contID, String userName) throws RemoteException;

	public void updateAccount(int contID, String user, String password) throws RemoteException;

	public void deleteAccount(int contID) throws RemoteException;
}
