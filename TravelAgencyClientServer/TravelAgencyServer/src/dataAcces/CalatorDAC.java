package dataAcces;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import contract.ICalatorDAC;
import entity.Calator;

public class CalatorDAC implements ICalatorDAC {

	@Override
	public List<String> getSellTravellers() throws RemoteException {
		List<String> travellers = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.nume FROM persoane p, calatori c WHERE c.persID=p.persID";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				travellers.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return travellers;
	}

	@Override
	public Calator getTravellerByName(String name) throws RemoteException {
		Calator traveller = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.persID, p.nume, p.varsta, p.adresa, p.email, p.nrTel, c.contBancar "
					+ "FROM persoane p, calatori c WHERE p.persID=c.persID and p.nume=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			result = statement.executeQuery();
			while (result.next()) {
				traveller = new Calator(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4),
						result.getString(5), result.getString(6), result.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return traveller;
	}

	@Override
	public void addSoldTicket(int persID, int nrBilet) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "INSERT INTO calatorbilete (persID,nrBilet) VALUES(?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, persID);
			statement.setInt(2, nrBilet);
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
