package dataAcces;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import connection.DBConnection;
import contract.IUtilizatorDAC;
import entity.ContUtilizator;
import entity.Utilizator;

public class UtilizatorDAC implements IUtilizatorDAC {

	@Override
	public String getUserWithCredentials(String userName, String password, String userType) throws RemoteException {
		String utilizator = "";
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.nume FROM persoane p,conturi c, utilizatori u "
					+ "WHERE c.userName=? and c.parola=? and c.contID=u.contID and u.tip=? and u.persID=p.persID";
			statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			statement.setString(3, userType);
			result = statement.executeQuery();
			while (result.next()) {
				utilizator = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return utilizator;
	}

	@Override
	public String getEmployees() throws RemoteException {
		List<Utilizator> utilizatori = new ArrayList<Utilizator>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.persID, p.nume, p.varsta, p.adresa, p.email, p.nrTel, u.tip, c.contID,c.userName, c.parola "
					+ "FROM persoane p, utilizatori u, conturi c "
					+ "WHERE u.tip='Employee' and c.contID=u.contID and u.persID=p.persID";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				utilizatori.add(new Utilizator(result.getInt(1), result.getString(2), result.getInt(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						new ContUtilizator(result.getInt(8), result.getString(9), result.getString(10))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return utilizatori.stream().map(s -> s.toString()).collect(Collectors.joining("\n\n")).toString();
	}

	@Override
	public List<String> getEmployeesNames() throws RemoteException {
		List<String> utilizatoriNume = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.nume " + "FROM persoane p, utilizatori u, conturi c "
					+ "WHERE u.tip='Employee' and c.contID=u.contID and u.persID=p.persID";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				utilizatoriNume.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return utilizatoriNume;
	}

	@Override
	public Utilizator getUserByName(String name) throws RemoteException {
		Utilizator utilizator = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT p.persID, p.nume, p.varsta, p.adresa, p.email, p.nrTel, u.tip, c.contID, c.userName, c.parola "
					+ "FROM persoane p, utilizatori u, conturi c "
					+ "WHERE u.tip='Employee' and c.contID=u.contID and u.persID=p.persID and p.nume=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			result = statement.executeQuery();
			while (result.next()) {
				utilizator = new Utilizator(result.getInt(1), result.getString(2), result.getInt(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7),
						new ContUtilizator(result.getInt(8), result.getString(9), result.getString(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return utilizator;
	}

	@Override
	public boolean isDuplicateUserInput(int persID, int contID) throws RemoteException {
		boolean isDuplicate = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query1 = "SELECT * FROM utilizatori WHERE persID=? and tip='Employee' and contID=?";
			statement = connection.prepareStatement(query1);
			statement.setInt(1, persID);
			statement.setInt(2, contID);
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
	public void addNewUser(int persID, int contID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "INSERT INTO utilizatori (persID,tip,contID) VALUES (?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, persID);
			statement.setString(2, "Employee");
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
	public void deleteUser(int persID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "DELETE FROM utilizatori WHERE persID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, persID);
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
