package godxero.control.user;

import godxero.db.DBConnection;
import godxero.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController implements UserService {
	private static UserController instance;

	private UserController () {}

	public static UserController getInstance () {
		if (UserController.instance == null) UserController.instance = new UserController();
		return UserController.instance;
	}

	@Override
	public User getUser (String userName) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `user` WHERE user_name = ?")) {
				statement.setString(1, userName);

				try (final ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) return new User(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getString(6)
					);
				}
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return null;
	}

	@Override
	public int addUser (User user) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO `user` (user_first_name, user_last_name, user_name, user_password, user_email) VALUES (?, ?, ?, ?, ?)")) {
				statement.setString(1, user.getFirstName());
				statement.setString(2, user.getLastName());
				statement.setString(3, user.getUserName());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getEmail());

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	@Override
	public int deleteUser (String userName) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("DELETE FROM `user` WHERE userName = ?")) {
				statement.setString(1, userName);

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	@Override
	public int updateUser (User user) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("UPDATE `user` SET user_first_name = ?, user_last_name = ?, user_name = ?, user_password = ?, user_email = ? WHERE user_name = ?")) {
				statement.setString(1, user.getFirstName());
				statement.setString(2, user.getLastName());
				statement.setString(3, user.getUserName());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getEmail());
				statement.setString(6, user.getUserName());

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	@Override
	public boolean isUsernameAvailable (String userName) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM `user` WHERE user_name = ?")) {
				statement.setString(1, userName);

				return statement.executeQuery().next();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return false;
	}
}
