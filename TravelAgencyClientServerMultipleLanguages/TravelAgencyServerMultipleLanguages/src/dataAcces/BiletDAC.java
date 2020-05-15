package dataAcces;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import contract.IBiletDAC;
import entity.Bilet;

public class BiletDAC implements IBiletDAC {

	@Override
	public List<Bilet> getAllTickets() throws RemoteException {
		List<Bilet> bilete = new ArrayList<Bilet>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM bilete";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				bilete.add(new Bilet(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getBoolean(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return bilete;
	}

	@Override
	public List<String> getTicketNumbers() throws RemoteException {
		List<String> numbers = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT nrBilet FROM bilete";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				numbers.add(String.valueOf(result.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return numbers;
	}

	@Override
	public Bilet getTicketByNumber(int number) throws RemoteException {
		Bilet bilet = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM bilete WHERE nrBilet=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, number);
			result = statement.executeQuery();
			while (result.next()) {
				bilet = new Bilet(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getBoolean(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return bilet;

	}

	@Override
	public List<String> getSellTickets() throws RemoteException {
		List<String> ticketNumbers = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT nrBilet FROM bilete WHERE isVandut=false";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				ticketNumbers.add(String.valueOf(result.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return ticketNumbers;
	}

	@Override
	public void updateSoldTicket(int nrTicket) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE bilete SET isVandut=true WHERE nrBilet=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, nrTicket);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

	@Override
	public boolean isDuplicateTicketInput(Bilet bilet) throws RemoteException {
		boolean isDuplicate = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM bilete WHERE nrBilet=? and plecare=? and sosire=? and "
					+ "data=? and pret=? and isVandut=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, bilet.getNrBilet());
			statement.setString(2, bilet.getPlecare());
			statement.setString(3, bilet.getSosire());
			statement.setString(4, bilet.getData());
			statement.setInt(5, bilet.getPret());
			statement.setBoolean(6, bilet.isVandut());
			result = statement.executeQuery();
			while (result.next()) {
				isDuplicate = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return isDuplicate;
	}

	@Override
	public int getlastTicketID() throws RemoteException {
		int ticketID = -1;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT MAX(nrBilet) FROM bilete";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				ticketID = result.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return ticketID;
	}

	@Override
	public void addNewTicket(Bilet bilet) throws RemoteException {
		int ticketID = getlastTicketID();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "INSERT INTO bilete (nrBilet,plecare,sosire,data,pret,isVandut) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, ticketID + 1);
			statement.setString(2, bilet.getPlecare());
			statement.setString(3, bilet.getSosire());
			statement.setString(4, bilet.getData());
			statement.setInt(5, bilet.getPret());
			statement.setBoolean(6, bilet.isVandut());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

	@Override
	public void deleteTicket(int ticketID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "DELETE FROM bilete WHERE nrBilet=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, ticketID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

	@Override
	public void updateTicket(int nrTicket, Bilet bilet) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE bilete SET plecare=?, sosire=?, data=?, pret=?, isVandut=? WHERE nrBilet=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, bilet.getPlecare());
			statement.setString(2, bilet.getSosire());
			statement.setString(3, bilet.getData());
			statement.setInt(4, bilet.getPret());
			statement.setBoolean(5, bilet.isVandut());
			statement.setInt(6, nrTicket);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

}
