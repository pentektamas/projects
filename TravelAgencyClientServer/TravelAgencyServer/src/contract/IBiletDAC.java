package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Bilet;

public interface IBiletDAC extends Remote {

	public List<Bilet> getAllTickets() throws RemoteException;

	public List<String> getTicketNumbers() throws RemoteException;

	public Bilet getTicketByNumber(int number) throws RemoteException;

	public List<String> getSellTickets() throws RemoteException;

	public void updateSoldTicket(int nrTicket) throws RemoteException;

	public boolean isDuplicateTicketInput(Bilet bilet) throws RemoteException;

	public int getlastTicketID() throws RemoteException;

	public void addNewTicket(Bilet bilet) throws RemoteException;

	public void deleteTicket(int ticketID) throws RemoteException;

	public void updateTicket(int nrTicket, Bilet bilet) throws RemoteException;

}
