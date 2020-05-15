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
import contract.IAvionDAC;
import entity.Avion;
import entity.Bilet;

public class AvionDAC implements IAvionDAC {

	public List<Avion> getAllPlanes() throws RemoteException {
		List<Avion> avioane = new ArrayList<Avion>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				avioane.add(new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avioane;

	}

	@Override
	public List<String> getPlaneNumbers() throws RemoteException {
		List<String> numbers = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT nrAvion FROM AVIOANE";
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
	public Avion getPlaneByNumber(int number) throws RemoteException {
		Avion avion = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE WHERE nrAvion=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, number);
			result = statement.executeQuery();
			while (result.next()) {
				avion = new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avion;

	}

	@Override
	public String getPlanesByDeparture(String departure) {

		List<Avion> avioane = new ArrayList<Avion>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE WHERE plecare=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, departure);
			result = statement.executeQuery();

			while (result.next()) {
				avioane.add(new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avioane.stream().map(s -> s.toString()).collect(Collectors.joining("\n\n")).toString();

	}

	@Override
	public String getPlanesByArrival(String arrival) {

		List<Avion> avioane = new ArrayList<Avion>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE WHERE sosire=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, arrival);
			result = statement.executeQuery();

			while (result.next()) {
				avioane.add(new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avioane.stream().map(s -> s.toString()).collect(Collectors.joining("\n\n")).toString();
	}

	@Override
	public String getPlanesByRoute(String departure, String arrival) {

		List<Avion> avioane = new ArrayList<Avion>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE WHERE plecare=? AND sosire=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, departure);
			statement.setString(2, arrival);
			result = statement.executeQuery();

			while (result.next()) {
				avioane.add(new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avioane.stream().map(s -> s.toString()).collect(Collectors.joining("\n\n")).toString();

	}

	@Override
	public String getPlanesByDuration(String duration) {

		List<Avion> avioane = new ArrayList<Avion>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM AVIOANE WHERE durataZborului=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(duration));
			result = statement.executeQuery();

			while (result.next()) {
				avioane.add(new Avion(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
						result.getInt(5), result.getString(6), result.getString(7), result.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return avioane.stream().map(s -> s.toString()).collect(Collectors.joining("\n\n")).toString();
	}

	@Override
	public List<String> getDepartures() throws RemoteException {
		List<String> departures = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT DISTINCT plecare FROM AVIOANE ORDER BY plecare";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			while (result.next()) {
				departures.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return departures;
	}

	@Override
	public List<String> getArrivals() throws RemoteException {
		List<String> arrivals = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT DISTINCT sosire FROM AVIOANE";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			while (result.next()) {
				arrivals.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return arrivals;
	}

	@Override
	public List<String> getDurations() throws RemoteException {
		List<String> durations = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT DISTINCT durataZborului FROM AVIOANE";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			while (result.next()) {
				durations.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return durations;
	}

	@Override
	public void changeAvailableSeatsNumber(Bilet bilet) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE avioane SET nrLocuri=nrLocuri-1 WHERE plecare=? and sosire=? and pretBilet=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, bilet.getPlecare());
			statement.setString(2, bilet.getSosire());
			statement.setInt(3, bilet.getPret());
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
	public boolean isDuplicatePlaneInput(Avion avion) throws RemoteException {
		boolean isDuplicate = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT * FROM avioane WHERE nrAvion=? and nrLocuri=? and tip=? and "
					+ "liniaAeriana=? and durataZborului=? and plecare=? and sosire=? and pretBilet=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, avion.getNrAvion());
			statement.setInt(2, avion.getNrLocuri());
			statement.setString(3, avion.getTip());
			statement.setString(4, avion.getLiniaAeriana());
			statement.setInt(5, avion.getDurataZborului());
			statement.setString(6, avion.getPlecare());
			statement.setString(7, avion.getSosire());
			statement.setInt(8, avion.getPretBilet());
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
	public int getlastPlaneID() throws RemoteException {
		int planeID = -1;
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "SELECT MAX(nrAvion) FROM avioane";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				planeID = result.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(statement);
			DBConnection.close(result);
			DBConnection.close(connection);
		}
		return planeID;
	}

	@Override
	public void addNewPlane(Avion avion) throws RemoteException {
		int planeID = getlastPlaneID();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "INSERT INTO avioane (nrAvion,nrLocuri,tip,liniaAeriana,durataZborului,plecare,sosire,pretBilet) VALUES (?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, planeID + 1);
			statement.setInt(2, avion.getNrLocuri());
			statement.setString(3, avion.getTip());
			statement.setString(4, avion.getLiniaAeriana());
			statement.setInt(5, avion.getDurataZborului());
			statement.setString(6, avion.getPlecare());
			statement.setString(7, avion.getSosire());
			statement.setInt(8, avion.getPretBilet());
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
	public void deletePlane(int planeID) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "DELETE FROM avioane WHERE nrAvion=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, planeID);
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
	public void updatePlane(int nrAvion, Avion avion) throws RemoteException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			String query = "UPDATE avioane SET nrLocuri=?, tip=?, liniaAeriana=?, "
					+ "durataZborului=?, plecare=?, sosire=?, pretBilet=? WHERE nrAvion=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, avion.getNrLocuri());
			statement.setString(2, avion.getTip());
			statement.setString(3, avion.getLiniaAeriana());
			statement.setInt(4, avion.getDurataZborului());
			statement.setString(5, avion.getPlecare());
			statement.setString(6, avion.getSosire());
			statement.setInt(7, avion.getPretBilet());
			statement.setInt(8, nrAvion);
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
