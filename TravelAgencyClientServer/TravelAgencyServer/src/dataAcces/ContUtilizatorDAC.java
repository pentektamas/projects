package dataAcces;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import contract.IContUtilizatorDAC;

public class ContUtilizatorDAC implements IContUtilizatorDAC {

	@Override
	public int getLastContID() throws RemoteException {
		int contID = -1;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT MAX(contID) FROM conturi";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				contID = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return contID;
	}

	@Override
	public boolean isDuplicateAccountInput(String user) throws RemoteException {
		boolean isDuplicate = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query1 = "SELECT userName FROM conturi WHERE userName=?";
			statement = connection.prepareStatement(query1);
			statement.setString(1, user);
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
	public void addNewAccount(String user, String password) throws RemoteException {
		int contID = getLastContID();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "INSERT INTO conturi (contID,userName,parola) VALUES (?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, contID + 1);
			statement.setString(2, user);
			statement.setString(3, password);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

	public boolean isDuplicateUsername(int contID, String userName) throws RemoteException {
		boolean isDuplicate = false;
		int count = -1;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT COUNT(*) FROM conturi WHERE userName=? and contID!=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setInt(2, contID);
			result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}
			if (count > 0)
				isDuplicate = true;
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
	public void updateAccount(int contID, String user, String password) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE conturi SET userName=?, parola=? WHERE contID=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, password);
			statement.setInt(3, contID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

	@Override
	public void deleteAccount(int contID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "DELETE FROM conturi WHERE contID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, contID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
	}

}
