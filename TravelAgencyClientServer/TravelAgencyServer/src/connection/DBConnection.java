package connection;

import java.sql.*;

public class DBConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/travelagency?useSSL=false";
	// private static final String DBURL =
	// "jdbc:mysql://localhost:3306/travelagency";
	// SET GLOBAL time_zone = '+2:00';
	private static final String USER = "root";
	private static final String PASSWORD = "password";

	private static DBConnection singleInstance = new DBConnection();

	private DBConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Error DBConnection constructor!");
		}
	}

	private Connection createConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error! Can't connect to database!");
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConnection() {
		Connection con = singleInstance.createConnection();
		return con;
	}

	public static void close(Connection connection) {
		try {
			if (connection != null && connection.isClosed() == false)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Error! Can't close the connection!");
		}
	}

	public static void close(Statement statement) {
		try {
			if (statement != null && statement.isClosed() == false)
				statement.close();
		} catch (SQLException e) {
			System.out.println("Error! Can't close the statement!");
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null && resultSet.isClosed() == false)
				resultSet.close();
		} catch (SQLException e) {
			System.out.println("Error! Can't close the resultSet!");
		}
	}
}
