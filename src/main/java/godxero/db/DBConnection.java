package godxero.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
	private static DBConnection instance;

	final private Connection connection;

	private DBConnection () throws SQLException {
		final String HOST = "localhost";
		final int PORT = 3306;
		final String DATABASE = "task_board";
		final String USER = "root";
		final String PASSWORD = "1234";

		this.connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE), USER, PASSWORD);
	}

	public static DBConnection getInstance () throws SQLException {
		if (DBConnection.instance == null) DBConnection.instance = new DBConnection();
		return DBConnection.instance;
	}
}
