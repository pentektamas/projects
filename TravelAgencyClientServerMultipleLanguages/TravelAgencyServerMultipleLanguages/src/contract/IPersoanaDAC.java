package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.Utilizator;

public interface IPersoanaDAC extends Remote {

	public int getlastPersID() throws RemoteException;

	public boolean isDuplicatePersonInput(Utilizator user) throws RemoteException;

	public void addNewPerson(Utilizator user) throws RemoteException;

	public void updatePerson(int persID, String name, int age, String phone) throws RemoteException;

	public void deletePerson(int persID) throws RemoteException;
}
