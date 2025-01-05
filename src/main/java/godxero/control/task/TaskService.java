package godxero.control.task;

import godxero.model.task.Task;

import java.util.List;

public interface TaskService {
	List<Task> getAllTasks (int userID);
	int addTask(Task task);
	int deleteTask(int taskID);
	int updateTask(Task task);
}
