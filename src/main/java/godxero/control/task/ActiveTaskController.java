package godxero.control.task;

import godxero.db.DBConnection;
import godxero.model.task.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveTaskController implements TaskService {
	private static ActiveTaskController instance;

	private ActiveTaskController () {}

	public static ActiveTaskController getInstance () {
		if (ActiveTaskController.instance == null) ActiveTaskController.instance = new ActiveTaskController();
		return ActiveTaskController.instance;
	}

	@Override
	public List<Task> getAllTasks (int userID) {
		final List<Task> tasks = new ArrayList<>();

		try {
			final Connection connection = DBConnection.getInstance().getConnection();

			try (final PreparedStatement statement = connection.prepareStatement("SELECT * FROM active_task WHERE user_id = ?")) {
				statement.setInt(1, userID);

				// Collect all tasks results and add each in to 'tasks' list.
				try (final ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) tasks.add(new ActiveTask(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						TaskCategory.fromString(resultSet.getString(5)),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						TaskPriority.fromString(resultSet.getString(9)),
						TaskFrequency.fromString(resultSet.getString(10))
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

			try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO active_task (user_id, task_description, task_title, task_category, task_start_time, task_update_time, task_schedule_time, task_priority, task_frequency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
				statement.setInt(1, task.getUserID());
				statement.setString(2, task.getDescription());
				statement.setString(3, task.getTitle());
				statement.setString(4, task.getCategory().toString());
				statement.setString(5, task.getStartTime());
				statement.setString(6, task.getUpdateTime());
				statement.setString(7, task.getScheduleTime());
				statement.setString(8, task.getPriority().toString());
				statement.setString(9, task.getFrequency().toString());

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

			try (final PreparedStatement statement = connection.prepareStatement("DELETE FROM active_task WHERE task_id = ?")) {
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

			try (final PreparedStatement statement = connection.prepareStatement("UPDATE active_task SET user_id = ?, task_description = ?, task_title = ?, task_category = ?, task_start_time = ?, task_update_time = ?, task_schedule_time = ?, task_priority = ?, task_frequency = ? WHERE task_id = ?")) {
				statement.setInt(1, task.getUserID());
				statement.setString(2, task.getDescription());
				statement.setString(3, task.getTitle());
				statement.setString(4, task.getCategory().toString());
				statement.setString(5, task.getStartTime());
				statement.setString(6, task.getUpdateTime());
				statement.setString(7, task.getScheduleTime());
				statement.setString(8, task.getPriority().toString());
				statement.setString(9, task.getFrequency().toString());
				statement.setInt(10, task.getTaskID());

				return statement.executeUpdate();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return 0;
	}

	public int completeTask (int taskID) {
		try {
			final Connection connection = DBConnection.getInstance().getConnection();
			connection.setAutoCommit(false);

			try (final PreparedStatement statement = connection.prepareStatement("SELECT * FROM active_task WHERE task_id = ?")) {
				statement.setInt(1, taskID);

				try (final ResultSet resultSet = statement.executeQuery()) {
					if (!resultSet.next()) return 0; // There is no task with 'taskID'.

					// Create new CompletedTask model with current time.
					final Task completedTask = new CompletedTask(
						-1,
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						TaskCategory.fromString(resultSet.getString(5)),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						TaskPriority.fromString(resultSet.getString(9)),
						TaskFrequency.fromString(resultSet.getString(10)),
						Task.getCurrentDateTime()
					);

					// If, created completed task successfully added to the 'completed_task' table and target active task removed from the 'active_task' table, then commit the changes and return success(1).
					if (CompletedTaskController.getInstance().addTask(completedTask) == 1 && ActiveTaskController.instance.deleteTask(taskID) == 1) {
						connection.commit();
						return 1;
					}
				}
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());

			try {
				// Rollback everything if any error happened.
				DBConnection.getInstance().getConnection().rollback();
			} catch (SQLException rollBackException) {
				System.out.println(rollBackException.getMessage());
			}
		} finally {
			try {
				// Enable auto commit in any case when exit from the method.
				DBConnection.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException exception) {
				System.out.println(exception.getMessage());
			}
		}

		return 0;
	}
}
