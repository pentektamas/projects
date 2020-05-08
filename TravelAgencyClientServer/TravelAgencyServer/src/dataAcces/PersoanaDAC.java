package dataAcces;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import contract.IPersoanaDAC;
import entity.Utilizator;

public class PersoanaDAC implements IPersoanaDAC {

	@Override
	public int getlastPersID() throws RemoteException {
		int persID = -1;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT MAX(persID) FROM persoane";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				persID = result.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return persID;
	}

	@Override
	public boolean isDuplicatePersonInput(Utilizator user) throws RemoteException {
		boolean isDuplicate = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM persoane WHERE nume=? and varsta=? and adresa=? and email=? and nrTel=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user.getNume());
			statement.setInt(2, user.getVarsta());
			statement.setString(3, user.getAdresa());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getNrTel());
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
	public void addNewPerson(Utilizator user) throws RemoteException {
		int persID = getlastPersID();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			String query = "INSERT INTO persoane (persID,nume,varsta,adresa,email,nrTel) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, persID + 1);
			statement.setString(2, user.getNume());
			statement.setInt(3, user.getVarsta());
			statement.setString(4, user.getAdresa());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getNrTel());
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
	public void updatePerson(int persID, String name, int age, String phone) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE persoane SET nume=?, varsta=?, nrTel=? WHERE persID=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, age);
			statement.setString(3, phone);
			statement.setInt(4, persID);
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
	public void deletePerson(int persID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "DELETE FROM persoane WHERE persID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, persID);
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
