package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Avion;
import entity.Bilet;

public interface IAvionDAC extends Remote {

	public List<Avion> getAllPlanes() throws RemoteException;

	public List<String> getPlaneNumbers() throws RemoteException;

	public Avion getPlaneByNumber(int nrPlane) throws RemoteException;

	public String getPlanesByDeparture(String departure) throws RemoteException;

	public String getPlanesByArrival(String arrival) throws RemoteException;

	public String getPlanesByRoute(String departure, String arrival) throws RemoteException;

	public String getPlanesByDuration(String duration) throws RemoteException;

	public List<String> getDepartures() throws RemoteException;

	public List<String> getArrivals() throws RemoteException;

	public List<String> getDurations() throws RemoteException;

	public void changeAvailableSeatsNumber(Bilet bilet) throws RemoteException;

	public boolean isDuplicatePlaneInput(Avion avion) throws RemoteException;

	public int getlastPlaneID() throws RemoteException;

	public void addNewPlane(Avion avion) throws RemoteException;

	public void deletePlane(int planeID) throws RemoteException;

	public void updatePlane(int nrAvion, Avion avion) throws RemoteException;

}
