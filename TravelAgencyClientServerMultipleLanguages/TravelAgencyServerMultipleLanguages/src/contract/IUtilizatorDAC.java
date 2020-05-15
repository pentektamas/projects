package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Utilizator;

public interface IUtilizatorDAC extends Remote {

	public String getUserWithCredentials(String userName, String password, String userType) throws RemoteException;

	public String getEmployees() throws RemoteException;

	public List<String> getEmployeesNames() throws RemoteException;

	public Utilizator getUserByName(String name) throws RemoteException;

	public boolean isDuplicateUserInput(int persID, int contID) throws RemoteException;

	public void addNewUser(int persID, int contID) throws RemoteException;

	public void deleteUser(int persID) throws RemoteException;

}
