package godxero.control.task;

import godxero.db.DBConnection;
import godxero.model.task.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompletedTaskController implements TaskService {
	private static CompletedTaskController instance;

	private CompletedTaskController () {}

	public static CompletedTaskController getInstance () {
		if (CompletedTaskController.instance == null) CompletedTaskController.instance = new CompletedTaskController();
		return CompletedTaskController.instance;
	}

	@Override
	public List<Task> getAllTasks (int userID) {
		final List<Task> tasks = new ArrayList<>();

		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("SELECT * FROM completed_task WHERE user_id = ?")) {
				statement.setInt(1, userID);

				// Collect all tasks results and add each in to 'tasks' list.
				try (final ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) tasks.add(new CompletedTask(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						TaskCategory.fromString(resultSet.getString(5)),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						TaskPriority.fromString(resultSet.getString(9)),
						TaskFrequency.fromString(resultSet.getString(10)),
						resultSet.getString(11)
					));
				}
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return tasks;
	}

	@Override
	public int addTask (Task task) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO completed_task (user_id, task_description, task_title, task_category, task_start_time, task_update_time, task_schedule_time, task_priority, task_frequency, completed_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
				statement.setInt(1, task.getUserID());
				statement.setString(2, task.getDescription());
				statement.setString(3, task.getTitle());
				statement.setString(4, task.getCategory().toString());
				statement.setString(5, task.getStartTime());
				statement.setString(6, task.getUpdateTime());
				statement.setString(7, task.getScheduleTime());
				statement.setString(8, task.getPriority().toString());
				statement.setString(9, task.getFrequency().toString());
				statement.setString(10, ((CompletedTask) task).getCompletedTime());

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	@Override
	public int deleteTask (int taskID) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("DELETE FROM completed_task WHERE task_id = ?")) {
				statement.setInt(1, taskID);

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	@Override
	public int updateTask (Task task) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("UPDATE completed_task SET user_id = ?, task_description = ?, task_title = ?, task_category = ?, task_start_time = ?, task_update_time = ?, task_schedule_time = ?, task_priority = ?, task_frequency = ?, completed_time = ? WHERE task_id = ?")) {
				statement.setInt(1, task.getUserID());
				statement.setString(2, task.getDescription());
				statement.setString(3, task.getTitle());
				statement.setString(4, task.getCategory().toString());
				statement.setString(5, task.getStartTime());
				statement.setString(6, task.getUpdateTime());
				statement.setString(7, task.getScheduleTime());
				statement.setString(8, task.getPriority().toString());
				statement.setString(9, task.getFrequency().toString());
				statement.setString(10, ((CompletedTask) task).getCompletedTime());
				statement.setInt(11, task.getTaskID());

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}
}
